package com.marieteck.gestionstock_backend.validator;

import com.marieteck.gestionstock_backend.dto.EnterpriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseValidator {

    //method to return error
    public static List<String> validate (EnterpriseDto enterpriseDto){
        List<String> errors = new ArrayList<String>();

        if (enterpriseDto == null) {
            errors.add("please enter the enterprise name");
            errors.add("please enter the address");
            errors.add("please enter the enterprise email");
            errors.add("please enter the enterprise phone number");
            errors.add("please enter the  enterprise city");


        }

        if (!StringUtils.hasLength(enterpriseDto.getNom())) {
            errors.add("please enter the enterprise name");


        }

        if (!StringUtils.hasLength(enterpriseDto.getEmail())) {
            errors.add("please enter the enterprise email");

        }

        if (!StringUtils.hasLength(enterpriseDto.getCodeFiscal())){
            errors.add("please enter the enterprise postal code");
        }

        if (!StringUtils.hasLength(enterpriseDto.getNumTel())) {
            errors.add("please enter the enterprise phone number");

        }
         if (enterpriseDto.getAddressDto() == null) {
             errors.add("please enter the address");
         }else {
             if(!StringUtils.hasLength(enterpriseDto.getAddressDto().getAdresse1())){
                 errors.add("please enter the  enterprise address");
             }
             if(!StringUtils.hasLength(enterpriseDto.getAddressDto().getCodePostale())){
                 errors.add("please enter the  enterprise postal code");

             }

             if(!StringUtils.hasLength(enterpriseDto.getAddressDto().getVille())){
                 errors.add("please enter the  enterprise city");
             }
             if(!StringUtils.hasLength(enterpriseDto.getAddressDto().getPays())){
                 errors.add("please enter the  enterprise country");
             }
         }

        return errors;
    }
}
