package com.marieteck.gestionstock_backend.validator;

import com.marieteck.gestionstock_backend.dto.ArticleDto;
import com.marieteck.gestionstock_backend.model.Article;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto articleDto) {
        List<String> errors = new ArrayList<String>();

        if (articleDto == null) {
            errors.add("codeArticle is required");
            errors.add("designation is required");
            errors.add("Please enter the unit price of the item ");
            errors.add("Please enter the VAT rate of the item ");
            errors.add("Please enter the Unit Price including VAT of the item ");
            return errors;

        }

        if (!StringUtils.hasLength(articleDto.getCodeArticle())) {
            errors.add("codeArticle is required");
        }

        if (!StringUtils.hasLength(articleDto.getDesignation())) {
            errors.add("designation is required");
        }

        if (articleDto.getPrixUnitaireHt() == null) {

            errors.add("Please enter the unit price of the item ");
        }

        if (articleDto.getTauxTva() == null) {

            errors.add("Please enter the VAT rate of the item ");
        }

        if (articleDto.getPrixUnitaireTtc() == null) {

            errors.add("Please enter the Unit Price including VAT of the item ");
        }



        return errors;
    }

}
