package com.marieteck.gestionstock_backend.validator;

import com.marieteck.gestionstock_backend.dto.VentesDto;
import com.marieteck.gestionstock_backend.model.Ventes;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VentesValidators {
    public static List<String> validate(VentesDto ventesDto) {
        List<String> errors = new ArrayList<>();

        if (ventesDto == null) {
            errors.add("Vente code is required");
            return errors;
        }

        if (!StringUtils.hasLength(ventesDto.getCode())) {
            errors.add("Vente code is required");
        }


        if (ventesDto.getDateVente() == null) {
            errors.add("Vente date of commande is required");

        }

        return errors;

    }
}
