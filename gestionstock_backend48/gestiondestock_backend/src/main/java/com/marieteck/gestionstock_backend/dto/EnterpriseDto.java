package com.marieteck.gestionstock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marieteck.gestionstock_backend.model.Address;
import com.marieteck.gestionstock_backend.model.Enterprise;
import com.marieteck.gestionstock_backend.model.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EnterpriseDto {

    private Long id;

    private String nom;


    private String description;


    private AddressDto addressDto;


    private String codeFiscal;


    private String photo;


    private String email;


    private String numTel;


    private String steWeb;

    @JsonIgnore
    private List<UsersDto> users;

    // fromEntity
    public static EnterpriseDto  fromEntity(Enterprise enterprise) {
        if (enterprise == null){
            return null;
        }
        return EnterpriseDto.builder()
                .id(enterprise.getId())
                .nom(enterprise.getNom())
                .description(enterprise.getDescription())
                .codeFiscal(enterprise.getCodeFiscal())
                .photo(enterprise.getPhoto())
                .email(enterprise.getEmail())
                .numTel(enterprise.getNumTel())
                .steWeb(enterprise.getSteWeb())
                .addressDto(AddressDto.fromEntity(enterprise.getAddress()))
                .build();
    }



    //ToEntity
    public static Enterprise toEntity(EnterpriseDto enterpriseDto) {
        if (enterpriseDto == null){
            return null;
        }

        Enterprise enterprise = new Enterprise();
        enterprise.setId(enterpriseDto.getId());
        enterprise.setNom(enterpriseDto.getNom());
        enterprise.setDescription(enterpriseDto.getDescription());
        enterprise.setCodeFiscal(enterpriseDto.getCodeFiscal());
        enterprise.setPhoto(enterpriseDto.getPhoto());
        enterprise.setEmail(enterpriseDto.getEmail());
        enterprise.setNumTel(enterpriseDto.getNumTel());
        enterprise.setSteWeb(enterpriseDto.getSteWeb());
        enterprise.setAddress(AddressDto.toEntity(enterpriseDto.getAddressDto()));
        return enterprise;
    }


}
