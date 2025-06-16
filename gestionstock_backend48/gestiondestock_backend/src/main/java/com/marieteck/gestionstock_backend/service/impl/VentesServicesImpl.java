package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.LigneVenteDto;
import com.marieteck.gestionstock_backend.dto.VentesDto;
import com.marieteck.gestionstock_backend.exception.EntityNotFoundException;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.model.Article;
import com.marieteck.gestionstock_backend.model.LigneVente;
import com.marieteck.gestionstock_backend.model.Ventes;
import com.marieteck.gestionstock_backend.repository.ArticleRepository;
import com.marieteck.gestionstock_backend.repository.LigneVenteRepository;
import com.marieteck.gestionstock_backend.repository.VentesRepository;
import com.marieteck.gestionstock_backend.service.VentesService;
import com.marieteck.gestionstock_backend.validator.VentesValidators;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class VentesServicesImpl implements VentesService {

    private VentesRepository ventesRepository;
    private ArticleRepository articleRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Override
    public VentesDto save(VentesDto ventesDto) {
        // validation

        List<String> errors = VentesValidators.validate(ventesDto);
        if (!errors.isEmpty()) {
            log.error("sale is save error: " + errors);
            throw new InvalidEntityException("sale is not available", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        // verification des articles pour chaque   lignes de ventes
        List<String> articleErrors = new ArrayList<>();
        ventesDto.getLigneVentes().forEach(ligVente ->{
            if (ligVente != null) {
                Optional<Article> article = articleRepository.findById(ligVente.getArticleDto().getId());
                if (article.isPresent()) {
                    articleErrors.add("no article with this ID"  + ligVente.getArticleDto().getId());
                }else{
                    articleErrors.add("unable to save article with this ID a sale order with a null id  ");
                }


            }
        });

        if (!articleErrors.isEmpty()) {
            log.error("sale is save error: " + errors);
            throw new InvalidEntityException("One or more article were not found in the DB,{}", ErrorCodes.VENTE_NOT_VALID, errors);
        }
        Ventes ventesSaved = ventesRepository.save(VentesDto.toEntity(ventesDto));
        ventesDto.getLigneVentes().forEach(ligVente -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligVente);
            ligneVente.setVente(ventesSaved);
            ligneVenteRepository.save(ligneVente);
        });

        return VentesDto.fromEntity(ventesSaved);
    }

    @Override
    public VentesDto findById(Long id) {
        if (id == null) {
            log.error("id is null");
            return null;

        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("no Sale not found in DB" +  id , ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VentesDto findByCode(String code) {
        if (code== null) {
            log.error(" code sale  is null");
            return null;

        }
        return ventesRepository.findByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("no Sale found in DB" + code , ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error(" vente id is null");
            return;
        }
        ventesRepository.deleteById(id);

    }
}
