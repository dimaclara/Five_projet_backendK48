package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.ArticleDto;
import com.marieteck.gestionstock_backend.dto.LigneCommandeClientDto;
import com.marieteck.gestionstock_backend.dto.LigneCommandeFournisseurDto;
import com.marieteck.gestionstock_backend.dto.LigneVenteDto;

import java.util.List;

public interface ArticleService {

    // methode qui prendra compte l'ajout et la modification d'un article

    ArticleDto save(ArticleDto articleDto);

    ArticleDto findById(Long id);

    List<ArticleDto> findAll();

    ArticleDto findByCodeArticle(String codeArticle);



    List<LigneVenteDto> findHistoriqueVentes(Long idArticle);

   List<LigneCommandeClientDto> findHistoriqueCommandeClient(Long idArticle);

   List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Long idArticle);

   List<ArticleDto> findAllArticleByIdCategory(Long idCategory);

    void delete(Long id);


}
