package com.marieteck.gestionstock_backend.dto;

import com.marieteck.gestionstock_backend.model.Address;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AddressDto {

    private Long id;

    private String adresse1;


    private String adresse2;


    private String ville;

    private String codePostale;

    private String pays;

    // fromEntity
    public static AddressDto fromEntity(Address address) {
        if (address == null) {
            return null;
        }
        return AddressDto.builder()
                .adresse1(address.getAdresse1())
                .adresse2(address.getAdresse2())
                .ville(address.getVille())
                .codePostale(address.getCodePostale())
                .pays(address.getPays())
                .build();
    }

    // toEntity
    public static Address toEntity(AddressDto  addressDto) {
        if (addressDto == null) {
            return null;
        }

        Address address = new Address();
        address.setAdresse1(addressDto.getAdresse1());
        address.setAdresse2(addressDto.getAdresse2());
        address.setVille(addressDto.getVille());
        address.setCodePostale(addressDto.getCodePostale());
        address.setPays(addressDto.getPays());
        return address;
    }
}
