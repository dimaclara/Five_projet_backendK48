package com.marieteck.gestionstock_backend.validator;

import com.marieteck.gestionstock_backend.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<String>();


        if (clientDto == null) {
            errors.add("please the name of the client");
            errors.add("pleasethe first name is mandatory");
            errors.add("please enter the client mail");
            errors.add("please insert photo");
            errors.add("please enter the client telephone number");
            errors.add("please enter the client address");
            errors.add("please enter the client postale code");

            return errors;
        }

        if (!StringUtils.hasLength(clientDto.getNom())){
            errors.add("please the name of the client");

        }


        if (!StringUtils.hasLength(clientDto.getPrenom())){
            errors.add("please the first name is mandatory");

        }

        if (!StringUtils.hasLength(clientDto.getMail())){
            errors.add("please enter the client mail");
        }

        if (!StringUtils.hasLength(clientDto.getPhoto())){
            errors.add("please insert photo");
        }


        if (!StringUtils.hasLength(clientDto.getNumTel())){
            errors.add("please enter the client telephone number");
        }

        if(clientDto.getAddressDto() == null){
            errors.add("please enter the client address");
        }else{
            if (!StringUtils.hasLength(clientDto.getAddressDto().getAdresse1())){
                errors.add("please enter the client address1");
            }

            if (!StringUtils.hasLength(clientDto.getAddressDto().getAdresse2())){
                errors.add("please enter the client address2");
            }

            if (!StringUtils.hasLength(clientDto.getAddressDto().getCodePostale())){
                errors.add("please enter the client postale code");
            }

            if (!StringUtils.hasLength(clientDto.getAddressDto().getVille())){
                errors.add("please enter the client city");
            }
            if (!StringUtils.hasLength(clientDto.getAddressDto().getPays())){
                errors.add("please enter the client country");
            }
        }


        return errors;
    }
}
