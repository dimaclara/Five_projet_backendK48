package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.FournisseurDto;
import com.marieteck.gestionstock_backend.exception.EntityNotFoundException;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.model.Fournisseur;
import com.marieteck.gestionstock_backend.model.LigneCommandeFournisseur;
import com.marieteck.gestionstock_backend.repository.ArticleRepository;
import com.marieteck.gestionstock_backend.repository.FournisseurRepository;
import com.marieteck.gestionstock_backend.repository.LigneCommandeFournisseurRepository;
import com.marieteck.gestionstock_backend.service.FournisseurService;
import com.marieteck.gestionstock_backend.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

import java.util.List;
import java.util.stream.Collectors;

@Service
 @Slf4j
public class FournisseurServiceImpl implements FournisseurService {
    private final View error;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    public FournisseurServiceImpl(View error) {
        this.error = error;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        // validation
        List<String> errors = FournisseurValidator.validate(fournisseurDto);
        if (errors == null) {
            log.error("supplier is not valid");
            throw new InvalidEntityException("supplier is not valid", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        Fournisseur fournisseurSaved = fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto));

        return  FournisseurDto.fromEntity(fournisseurSaved);
    }

    @Override
    public FournisseurDto findById(Long id) {
        if (id  == null){
            log.error("supplier is not valid with this id");
            return null;
        }

        return fournisseurRepository.findById(id)
                .map(FournisseurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("no Suplier found with this id " + id , ErrorCodes.FOURNISSEUR_NOT_FOUND ));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll()
                .stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id  == null){
            log.error("supplier is not valid with this id");
            return ;
        }
        fournisseurRepository.deleteById(id);

    }
}
