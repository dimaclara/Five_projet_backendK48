package com.marieteck.gestionstock_backend.dto;

import com.marieteck.gestionstock_backend.model.Article;
import com.marieteck.gestionstock_backend.model.Category;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder

public class ArticleDto {

    private Long id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategoryDto categoryDto;

    private Integer idEnterprise;


    //fromEntitu

    public static ArticleDto fromEntity(Article article) {
        if (article == null) {
            return null;
        }

        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .photo(article.getPhoto())
                .idEnterprise(article.getIdEntreprise())
                .categoryDto(CategoryDto.fromEntity(article.getCategory()))
                .build();
    }


    //ToEntity

    public static Article toEntity(ArticleDto articleDto) {
        if (articleDto == null) {
            return null;
        }
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPhoto(articleDto.getPhoto());
        article.setIdEntreprise(articleDto.getIdEnterprise());
        article.setCategory(CategoryDto.toEntity(articleDto.getCategoryDto()));
        return article;
    }

}
