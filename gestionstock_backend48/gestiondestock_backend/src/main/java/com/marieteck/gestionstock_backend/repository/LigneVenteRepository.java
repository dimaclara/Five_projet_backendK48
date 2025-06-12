package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.Article;
import com.marieteck.gestionstock_backend.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Long> {
    List<Article> findAllByArticleId(Long idArticle);
}
