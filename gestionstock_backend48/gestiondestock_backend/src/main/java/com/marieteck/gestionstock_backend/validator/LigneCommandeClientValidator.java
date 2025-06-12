package com.marieteck.gestionstock_backend.validator;

import com.marieteck.gestionstock_backend.dto.LigneCommandeClientDto;


import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {

    public static List<String> validate(LigneCommandeClientDto ligneCommandeClientDto){
        List<String> errors = new ArrayList<String>();

         if(ligneCommandeClientDto == null){
             errors.add("please enter the prix unitaire");
             errors.add("please enter the article");
             errors.add("please enter the customer order line");
             return errors;
         }

        if (ligneCommandeClientDto.getArticleDto() == null || ligneCommandeClientDto.getArticleDto().getId() == null) {
            errors.add("please enter the article");

        }

        if (ligneCommandeClientDto.getCommandeClientDto() == null || ligneCommandeClientDto.getCommandeClientDto().getId() == null) {
            errors.add("please enter the customer order line");

        }

        if (ligneCommandeClientDto.getQuantite() == null) {
            errors.add("please enter the quantite of order line");

        }

        if (ligneCommandeClientDto.getPrixUnitaire() == null) {
            errors.add("please enter the prix unitaire");

        }





        return errors;

    }

}
