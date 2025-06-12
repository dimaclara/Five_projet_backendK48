package com.marieteck.gestionstock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marieteck.gestionstock_backend.model.Article;
import com.marieteck.gestionstock_backend.model.LigneVente;
import com.marieteck.gestionstock_backend.model.Ventes;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder

public class LigneVenteDto {

    private Long id;

    private VentesDto venteDto;

    private ArticleDto articleDto;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;


    // fromEntity
    public static LigneVenteDto fromEntity(LigneVente ligneVente) {
        //condition
        if (ligneVente == null) {
            return null;
        }

       return LigneVenteDto.builder()
               .id(ligneVente.getId())
               .venteDto(VentesDto.fromEntity(ligneVente.getVente()))
               .articleDto(ArticleDto.fromEntity(ligneVente.getArticle()))
               .quantite(ligneVente.getQuantite())
               .prixUnitaire(ligneVente.getPrixUnitaire())
               .build();
    }


    // ToEntity

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {
        //Condition
        if (ligneVenteDto == null) {
            return null;

            // TODO throw an exception
        }

        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setQuantite(ligneVente.getQuantite());
        ligneVente.setVente(VentesDto.toEntity(ligneVenteDto.getVenteDto()));
        ligneVente.setArticle(ArticleDto.toEntity(ligneVenteDto.getArticleDto()));
        ligneVente.setPrixUnitaire(ligneVente.getPrixUnitaire());
        return ligneVente;
    }



}
