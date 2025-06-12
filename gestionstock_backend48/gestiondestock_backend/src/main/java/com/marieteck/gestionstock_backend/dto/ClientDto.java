package com.marieteck.gestionstock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marieteck.gestionstock_backend.model.Address;
import com.marieteck.gestionstock_backend.model.Client;
import com.marieteck.gestionstock_backend.model.CommandeClient;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class ClientDto {

    private Long id;

    private String nom;


    private String prenom;


    private AddressDto addressDto;


    private String photo;


    private String mail;


    private String numTel;


    private Integer idEnterprise;

    @JsonIgnore
    private List<CommandeClientDto> commandeClients;

    //FromEntity

    public static ClientDto fromEntity(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .addressDto(AddressDto.fromEntity(client.getAddress()))
                .photo(client.getPhoto())
                .mail(client.getMail())
                .numTel(client.getNumTel())
                .idEnterprise(client.getIdEnterprise())
                .build();
    }

    // ToEntity
    public static Client toEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setAddress(AddressDto.toEntity(clientDto.getAddressDto()));
        client.setPhoto(clientDto.getPhoto());
        client.setMail(clientDto.getMail());
        client.setNumTel(clientDto.getNumTel());
        client.setIdEnterprise(clientDto.getIdEnterprise());
        return client;



    }



}
