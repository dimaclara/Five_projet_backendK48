package com.marieteck.gestionstock_backend.dto;

import com.marieteck.gestionstock_backend.model.Article;
import com.marieteck.gestionstock_backend.model.CommandeFournisseur;
import com.marieteck.gestionstock_backend.model.LigneCommandeFournisseur;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder

public class LigneCommandeFournisseurDto {

    private Long id;

    private ArticleDto articleDto;


    private CommandeFournisseurDto commandeFournisseurDto;


    private BigDecimal quantite;


    private BigDecimal prixUnitaire;


    // FromEntity
    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
        if (ligneCommandeFournisseur == null) {
            return null;
            //TODO Throw an exception
        }
        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .articleDto(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .commandeFournisseurDto(CommandeFournisseurDto.fromEntity(ligneCommandeFournisseur.getCommandeFournisseur()))
                .build();
    }


    // ToEntity
    public LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto  ligneCommandeFournisseurDto) {
        // condition
        if (ligneCommandeFournisseurDto == null) {
            return null;
        }
        LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(ligneCommandeFournisseurDto.getId());
        ligneCommandeFournisseur.setArticle(ArticleDto.toEntity(ligneCommandeFournisseurDto.getArticleDto()));
        ligneCommandeFournisseur.setCommandeFournisseur(CommandeFournisseurDto.toEntity(ligneCommandeFournisseurDto.getCommandeFournisseurDto()));
        ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseur.getQuantite());
        ligneCommandeFournisseur.setPrixUnitaire(ligneCommandeFournisseur.getPrixUnitaire());
        return ligneCommandeFournisseur;


    }


}
