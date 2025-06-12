package com.marieteck.gestionstock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marieteck.gestionstock_backend.model.Article;
import com.marieteck.gestionstock_backend.model.CommandeClient;
import com.marieteck.gestionstock_backend.model.LigneCommandeClient;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder

public class LigneCommandeClientDto {

    private Long id;

    private ArticleDto articleDto;
    @JsonIgnore
    private CommandeClientDto commandeClientDto;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;


    // FromEntity

    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
        if ( ligneCommandeClient == null) {
            return null;
        }
        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .articleDto(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                .commandeClientDto(CommandeClientDto.fromEntity(ligneCommandeClient.getCommandeClient()))
                .build();
    }


    // ToEntity
    public LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
        if ( ligneCommandeClientDto == null) {
            return null;
            //TODO Throw an exception
        }

        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
        ligneCommandeClient.setId(ligneCommandeClientDto.getId());
        ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
        ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());
        ligneCommandeClient.setArticle(ArticleDto.toEntity(ligneCommandeClientDto.getArticleDto()));
        ligneCommandeClient.setCommandeClient(CommandeClientDto.toEntity(ligneCommandeClientDto.getCommandeClientDto()));
        return ligneCommandeClient;

    }



}
