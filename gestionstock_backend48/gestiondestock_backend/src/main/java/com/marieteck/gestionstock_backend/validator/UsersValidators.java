package com.marieteck.gestionstock_backend.validator;

import com.marieteck.gestionstock_backend.dto.CategoryDto;
import com.marieteck.gestionstock_backend.dto.UsersDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UsersValidators {

    // methode qui retourne les errors
    public static List<String> validate(UsersDto usersDto) {
        List<String> errors = new ArrayList<String>();

        if (usersDto == null) {

            errors.add("please enter  the user's first name");
            errors.add("please enter  the user's first name");
            errors.add("please enter  the user's mote de-passe");
            errors.add("please enter the user's email");

        }

        if (usersDto == null || !StringUtils.hasLength(usersDto.getNom())) {
            errors.add("please enter the name of the user");
        }
        if (usersDto == null || !StringUtils.hasLength(usersDto.getPrenom())) {
            errors.add("please enter  the user's first name");
        }
        if (usersDto == null || !StringUtils.hasLength(usersDto.getMoteDePasse())) {
            errors.add("please enter  the user's mote de-passe");
        }
        if (usersDto == null || !StringUtils.hasLength(usersDto.getEmail())) {
            errors.add("please enter the user's email");
        }

        // validation sur la date de naissance

        if (usersDto.getDateDeNaissance() == null){
            errors.add("please enter the user's date de-naissance");
        }

        if ( usersDto.getAddressDto() == null) {
            errors.add("please enter the user's address");
        }else {
            if (!StringUtils.hasLength(usersDto.getAddressDto().getAdresse1())) {
                errors.add("the field address1 is mandatory ");

            }
            if (!StringUtils.hasLength(usersDto.getAddressDto().getCodePostale())) {
                errors.add(" the field postal code is mandatory ");

            }
            if (!StringUtils.hasLength(usersDto.getAddressDto().getPays())) {
                errors.add("  the field country is mandatory ");

            }
        }
        return errors;

    }




}
