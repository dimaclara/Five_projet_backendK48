package com.marieteck.gestionstock_backend.validator;

import com.marieteck.gestionstock_backend.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {

    public static List<String> valide(CommandeFournisseurDto commandeFournisseurDto){
        List<String> errors = new ArrayList<>();

        if (commandeFournisseurDto == null){
            errors.add("please enter the commandefournisseur etat");
            errors.add("please enter the commandefournisseur code");
            errors.add("please enter the commandefournisseur date");
            errors.add("please enter the commandefournisseur etat");
            errors.add("please enter the supplier");
            return errors;
        }

        if (!StringUtils.hasLength(commandeFournisseurDto.getCode())) {
            errors.add("please enter the commandefournisseur code");

        }

        if (commandeFournisseurDto.getDateCommande() == null) {
            errors.add("please enter the commandefournisseur date");

        }

        if(!StringUtils.hasLength(commandeFournisseurDto.getEtatCommande().toString())){
            errors.add("please enter the commandefournisseur etat");
        }

        if (commandeFournisseurDto.getFournisseurDto() == null || commandeFournisseurDto.getFournisseurDto().getId() == null) {
            errors.add("please enter the supplier");

        }


        return errors;
    }
}
