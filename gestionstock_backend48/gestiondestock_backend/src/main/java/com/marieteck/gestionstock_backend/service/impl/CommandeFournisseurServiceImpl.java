package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.CommandeFournisseurDto;
import com.marieteck.gestionstock_backend.dto.LigneCommandeFournisseurDto;
import com.marieteck.gestionstock_backend.exception.EntityNotFoundException;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.model.Article;
import com.marieteck.gestionstock_backend.model.CommandeFournisseur;
import com.marieteck.gestionstock_backend.model.Fournisseur;
import com.marieteck.gestionstock_backend.model.LigneCommandeFournisseur;
import com.marieteck.gestionstock_backend.repository.ArticleRepository;
import com.marieteck.gestionstock_backend.repository.CommandeFournisseurRepository;
import com.marieteck.gestionstock_backend.repository.FournisseurRepository;
import com.marieteck.gestionstock_backend.repository.LigneCommandeFournisseurRepository;
import com.marieteck.gestionstock_backend.service.CommandeFounisseurService;
import com.marieteck.gestionstock_backend.validator.CommandeFournisseurValidator;
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
public class CommandeFournisseurServiceImpl implements CommandeFounisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private ArticleRepository articleRepository;



    public CommandeFournisseurDto save(CommandeFournisseurDto commandFournisseurDto) {

        // verification de premier niveau
        List<String> errors = CommandeFournisseurValidator.validate(commandFournisseurDto);
        if (errors.isEmpty()) {
            log.error("supplier order is not valide {}",commandFournisseurDto);
            throw new InvalidEntityException("supplier orders is not available", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);

        }
        // verification Metier
        // verifier que le fournisseur existe avec ce ID dans la commande fournisseur

        Optional<Fournisseur>  fournisseur = fournisseurRepository.findById(commandFournisseurDto.getFournisseurDto().getId());
        if (fournisseur.isPresent()) {
            log.error("fournisseur already exists {}",commandFournisseurDto.getFournisseurDto().getId());
            throw new EntityNotFoundException("not supplier with this ID" + commandFournisseurDto.getFournisseurDto().getId() + " was not found");

        }
        // verify article ID is present in ligne commande client

        List<String> articleErrors = new ArrayList<>();
        if (commandFournisseurDto.getLigneCommandeFournisseurs() != null) {
            commandFournisseurDto.getLigneCommandeFournisseurs().forEach(ligCmdtFournisseur -> {
                if (ligCmdtFournisseur != null) {
                    Optional<Article> article = articleRepository.findById(ligCmdtFournisseur.getArticleDto().getId());
                    if (article.isPresent()) {
                        articleErrors.add("article with ID " + ligCmdtFournisseur.getId() + " already exists");
                    }else {
                        articleErrors.add("unable to  save with a supplier order with a null id supplier");
                    }


                }
            });

        }
        if (!articleErrors.isEmpty()) {
            log.warn("");
            throw new InvalidEntityException("Article not available in DB",ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);

        }

        // Enregistrer la command founisseur en faisant le mapping de l'objet Dto vers l'objet fournisseur

        CommandeFournisseur commandeFournisseurSaved = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandFournisseurDto));
        if (commandFournisseurDto.getLigneCommandeFournisseurs() !=null) {
            commandFournisseurDto.getLigneCommandeFournisseurs().forEach(ligComdFournisseur->{
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligComdFournisseur);
                ligneCommandeFournisseur.setCommandeFournisseur(commandeFournisseurSaved);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);

            });

        }

        return CommandeFournisseurDto.fromEntity(commandeFournisseurSaved);
    }
    @Override
    public List<CommandeFournisseurDto> findAll() {

        return commandeFournisseurRepository.findAll()
                .stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public CommandeFournisseurDto findById(Long id) {

        if (id == null) {
            log.error("supplier command id is NULL");
            return null;
        }
        return  commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("No supplier Order found with this id " + id, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));

    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (code == null) {
            log.error("supplier code is NULL");
            return null;
        }
        return commandeFournisseurRepository.findByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("nosupplier order found with this code" + code , ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }


    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("supplier code is NULL");
            return;

        }
        commandeFournisseurRepository.deleteById(id);
    }
}
