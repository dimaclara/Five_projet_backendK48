package com.marieteck.gestionstock_backend.validator;


import com.marieteck.gestionstock_backend.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validate(FournisseurDto fournisseurDto) {
        List<String> errors = new ArrayList<String>();


        if (fournisseurDto == null) {
            errors.add("please the name of the fournisseur");
            errors.add("pleasethe first name is mandatory");
            errors.add("please enter the fournisseur mail");
            errors.add("please insert photo");
            errors.add("please enter the fournisseur telephone number");
            errors.add("please enter the fournisseur address");
            errors.add("please enter the fournisseur postale code");

            return errors;
        }

        if (!StringUtils.hasLength(fournisseurDto.getNom())){
            errors.add("please the name of the fournisseur");

        }


        if (!StringUtils.hasLength(fournisseurDto.getPrenom())){
            errors.add("please the first name is mandatory");

        }

        if (!StringUtils.hasLength(fournisseurDto.getEmail())){
            errors.add("please enter the fournisseur email");
        }

        if (!StringUtils.hasLength(fournisseurDto.getPhoto())){
            errors.add("please insert photo");
        }


        if (!StringUtils.hasLength(fournisseurDto.getNumTel())){
            errors.add("please enter the fournisseur telephone number");
        }

        if(fournisseurDto.getAddressDto() == null){
            errors.add("please enter the fournisseur address");
        }else{
            if (!StringUtils.hasLength(fournisseurDto.getAddressDto().getAdresse1())){
                errors.add("please enter the fournisseur address1");
            }

            if (!StringUtils.hasLength(fournisseurDto.getAddressDto().getAdresse2())){
                errors.add("please enter the fournisseur address2");
            }

            if (!StringUtils.hasLength(fournisseurDto.getAddressDto().getCodePostale())){
                errors.add("please enter the fournisseur postale code");
            }

            if (!StringUtils.hasLength(fournisseurDto.getAddressDto().getVille())){
                errors.add("please enter the fournisseur city");
            }
            if (!StringUtils.hasLength(fournisseurDto.getAddressDto().getPays())){
                errors.add("please enter the fournisseur country");
            }
        }


        return errors;
    }
}
