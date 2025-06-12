package com.marieteck.gestionstock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marieteck.gestionstock_backend.model.Address;
import com.marieteck.gestionstock_backend.model.CommandeFournisseur;
import com.marieteck.gestionstock_backend.model.Fournisseur;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FournisseurDto {

    private Long id;

    private String nom;


    private String prenom;


    private AddressDto addressDto;


    private String photo;


    private String email;


    private String numTel;


    private Integer idEnterprise;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;

    // fromEntity

    public static FournisseurDto fromEntity(Fournisseur fournisseur) {
        // conditions
        if (fournisseur == null) {
            return null;
        }
        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .addressDto(AddressDto.fromEntity(fournisseur.getAddress()))

                .photo(fournisseur.getPhoto())
                .email(fournisseur.getEmail())
                .numTel(fournisseur.getNumTel())
                .idEnterprise(fournisseur.getIdEnterprise())
                .build();
    }


    // ToEntity
    public static Fournisseur toEntity(FournisseurDto fournisseurDto) {
        if (fournisseurDto == null) {
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setAddress(AddressDto.toEntity(fournisseurDto.getAddressDto()));
        fournisseur.setPhoto(fournisseurDto.getPhoto());
        fournisseur.setEmail(fournisseurDto.getEmail());
        fournisseur.setNumTel(fournisseurDto.getNumTel());
        fournisseur.setIdEnterprise(fournisseurDto.getIdEnterprise());
        return fournisseur;

    }

}
