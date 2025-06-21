package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.*;

import com.marieteck.gestionstock_backend.exception.EntityNotFoundException;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.model.*;
import com.marieteck.gestionstock_backend.repository.*;
import com.marieteck.gestionstock_backend.service.ArticleService;
import com.marieteck.gestionstock_backend.validator.ArticleValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;



@Service
@Slf4j
@AllArgsConstructor

public class ArticleServiceImpl implements ArticleService {


    private  ArticleRepository articleRepository;
    private LigneVenteRepository ligneVenteRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;




    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if (!errors.isEmpty()) {
            log.error("Article is not valid  {}", articleDto);
            throw new InvalidEntityException("article is not available", ErrorCodes.ARTICLE_ALREADY_IN_USE,errors);

        }
        return  ArticleDto.fromEntity(
                articleRepository.save(
                        ArticleDto.toEntity(articleDto)
                )
        );
    }



 public ArticleDto findById(Long id) {
        if (id == null) {
            log.error("id is null");
            return null;
        }

        return articleRepository.findById(id)
                .map(article -> ArticleDto.fromEntity(article)).orElseThrow(()->
                new EntityNotFoundException("not article with this id =" + id +
                        "not available in DB",
                        ErrorCodes.ARTICLE_ALREADY_IN_USE)
        );
 }


 public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(article -> ArticleDto.fromEntity(article))
                .collect(Collectors.toList());

 }


    @Override
 public ArticleDto findByCodeArticle(String codeArticle) {
        if (!StringUtils.hasLength(codeArticle)) {
            log.error("codeArticle is null");
            return null;
        }
        return articleRepository.findByCodeArticle(codeArticle)
                .map(ArticleDto::fromEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException("no article has this code " + codeArticle +
                                "not found in DB", ErrorCodes.ARTICLE_NOT_FOUND)

                );
    }

 public  List<LigneVenteDto> findHistoriqueVentes(Long idArticle){
        if (idArticle == null) {
            log.error("idArticle is null");
            return null;
        }
        return ligneVenteRepository.findAllByArticleId(idArticle).stream()
                .map(article -> LigneVenteDto.fromEntity(new LigneVente()))
                .collect(Collectors.toList());
 }


    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandeClient(Long idArticle) {
        if (idArticle == null) {
            log.error("idArticle is null");
            return null;
        }
        return ligneCommandeClientRepository.findHistoriqueCommandeClient(idArticle).stream()
                .map(article->LigneCommandeClientDto.fromEntity(new LigneCommandeClient()))
                .collect(Collectors.toList());
    }




    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Long idArticle) {

        return ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idArticle).stream()
                .map( article ->LigneCommandeFournisseurDto.fromEntity(new LigneCommandeFournisseur()))
                .collect(Collectors.toList());
    }


    public List<ArticleDto> findAllArticleByIdCategory(Long idCategory){
        if (idCategory == null) {
           log.error("idCategory is null");
           return null;
       }
        return articleRepository.findAllArticleByIdCategory(idCategory).stream()
                .map(article -> ArticleDto.fromEntity(new Article()))
                .collect(Collectors.toList());
    }


    public void deleteById(Long id){
        if (id == null) {
            log.error("id is null");
            return;

        }

        articleRepository.deleteById(id);
    }








}
