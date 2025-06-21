package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.dto.ArticleDto;
import com.marieteck.gestionstock_backend.dto.LigneVenteDto;
import com.marieteck.gestionstock_backend.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

   Optional<Article> findByCodeArticle(String codeArticle);

    @Query("SELECT a FROM Article a WHERE a.category.id = :idCategory")
   List<ArticleDto> findAllArticleByIdCategory(@Param("idArticle") Long idCategory);





}
//@Query("SELECT lcc FROM LigneCommandeClient lcc " +
//        "WHERE lcc.article.id = :idArticle " +
//        "ORDER BY lcc.commandeClient.dateCommande DESC")