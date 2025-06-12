package com.marieteck.gestionstock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marieteck.gestionstock_backend.model.Address;
import com.marieteck.gestionstock_backend.model.Enterprise;
import com.marieteck.gestionstock_backend.model.Roles;
import com.marieteck.gestionstock_backend.model.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
public class UsersDto {
    private Long id;

    private String nom;


    private String prenom;


    private String email;


    private Instant dateDeNaissance;


    private String moteDePasse;


    private AddressDto addressDto;

    private String photo;

    private EnterpriseDto entrepriseDto;


    private List<RolesDto> roles;

    //FromEntity

    public static UsersDto fromEntity(Users users) {
        //condition
        if (users == null) {
            return null;
        }
        return UsersDto.builder()
                .nom(users.getNom())
                .prenom(users.getPrenom())
                .email(users.getEmail())
                .dateDeNaissance(users.getDateDeNaissance())
                .moteDePasse(users.getMoteDePasse())
                .addressDto(AddressDto.fromEntity(users.getAddress()))
                .entrepriseDto(EnterpriseDto.fromEntity(users.getEnterprise()))
                .roles(
                        users.getRoles() != null?
                                users.getRoles().stream()
                                        .map(RolesDto::fromEntity)
                                        .collect(Collectors.toList()):null

                )




                .photo(users.getPhoto())
                .build();
    }

    // ToEntity
    public static Users toEntity(UsersDto usersDto) {
        if (usersDto == null) {
            return null;
            // TODO throw an exception
        }

        Users users = new Users();
        users.setNom(usersDto.getNom());
        users.setPrenom(usersDto.getPrenom());
        users.setEmail(usersDto.getEmail());
        users.setDateDeNaissance(usersDto.getDateDeNaissance());
        users.setMoteDePasse(usersDto.getMoteDePasse());
        users.setAddress(AddressDto.toEntity(usersDto.getAddressDto()));
        users.setEnterprise(EnterpriseDto.toEntity(usersDto.getEntrepriseDto()));
        users.setPhoto(usersDto.getPhoto());
        return users;
    }
}
