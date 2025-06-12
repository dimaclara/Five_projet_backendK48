package com.marieteck.gestionstock_backend.validator;

import com.marieteck.gestionstock_backend.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {
    public static List<String> validate (CommandeClientDto commandeClientDto){
        List<String> errors = new ArrayList<>();

        if (commandeClientDto == null) {
            errors.add("enter the commande client code");
            errors.add("enter the commande client date");
            errors.add("enter the customer's order status");
            errors.add("enter the customer's  id");
            return errors;

        }

        if (!StringUtils.hasLength(commandeClientDto.getCode())) {
            errors.add("enter the commande client code");

        }

        if (commandeClientDto.getDateCommande() == null) {
            errors.add("enter the commande client date");

        }

        if (commandeClientDto.getEtatCommande() == null) {
            errors.add("enter the customer's order status");

        }

        if(commandeClientDto.getClientDto() == null || commandeClientDto.getClientDto().getId() == null){
            errors.add("enter the customer's  id");
        }

        return errors;
    }
}
