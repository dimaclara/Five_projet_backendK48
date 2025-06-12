package com.marieteck.gestionstock_backend.dto;

import com.marieteck.gestionstock_backend.model.Article;
import com.marieteck.gestionstock_backend.model.MvtStk;
import com.marieteck.gestionstock_backend.model.SourceMvtStk;
import com.marieteck.gestionstock_backend.model.TypeMvtStk;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder

public class MvtStkDto {

    private Long id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private ArticleDto articleDto;

    private TypeMvtStk typeMvt;

    // FromEntity

    public MvtStkDto fromEntity(MvtStk mvtStk) {
        // Condition

        if (mvtStk == null) {
            return null;
        }

        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .articleDto(ArticleDto.fromEntity(mvtStk.getArticle()))
                .typeMvt(mvtStk.getTypeMvt())
                .build();
    }


    // toEntity

    public MvtStk toEntity(MvtStkDto mvtStkDto) {
        // condition
        if (mvtStkDto == null) {
            return null;

            //TODO Throw an exception
        }
        MvtStk mvtStk = new MvtStk();
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setDateMvt(mvtStkDto.getDateMvt());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setArticle(ArticleDto.toEntity(mvtStkDto.getArticleDto()));
        mvtStk.setTypeMvt(mvtStkDto.getTypeMvt());
        return mvtStk;
    }



}
