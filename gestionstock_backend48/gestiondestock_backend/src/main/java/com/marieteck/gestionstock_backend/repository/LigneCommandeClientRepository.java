package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.dto.LigneCommandeClientDto;
import com.marieteck.gestionstock_backend.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Long> {

    @Query("SELECT lcc FROM LigneCommandeClient lcc " +
            "WHERE lcc.article.id = :idArticle " +
            "ORDER BY lcc.commandeClient.dateCommande DESC")
    List<LigneCommandeClient> findHistoriqueCommandeClient(@Param("idArticle") Long idArticle);
}
