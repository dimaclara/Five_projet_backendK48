package com.marieteck.gestionstock_backend.validator;

import com.marieteck.gestionstock_backend.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {
    // methods qui retoune les errors

    public static List<String> validate(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<String>();

        if (categoryDto == null || !StringUtils.hasLength(categoryDto.getCode())) {
            errors.add("please enter de code of this category");
        }


        return errors;

    }

}
