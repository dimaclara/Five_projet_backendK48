package com.marieteck.gestionstock_backend.validator;

import com.marieteck.gestionstock_backend.dto.MvtStkDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {
    public static List<String> validate(MvtStkDto mvtStkDto){
        List<String> errors = new ArrayList<String>();

        if(mvtStkDto == null){
            errors.add("enter the stock quantity of mvt stk");
            errors.add("enter the date of MvtStk");
            errors.add("enter the article of MvtStk");
            errors.add("enter the type MvtStk");
            return errors;
        }

        if (mvtStkDto.getQuantite() == null|| mvtStkDto.getQuantite().compareTo(BigDecimal.ZERO) ==0) {
            errors.add("enter the stock quantity of mvt stk");

        }

        if (mvtStkDto.getDateMvt() == null) {
            errors.add("enter the date of MvtStk");

        }

        if (mvtStkDto.getArticleDto() == null || mvtStkDto.getArticleDto().getId() == null) {
            errors.add("enter the article of MvtStk");
        }

        if (!StringUtils.hasLength(mvtStkDto.getTypeMvt().name())){
            errors.add("enter the type MvtStk");
        }

        return errors;
    }
}
