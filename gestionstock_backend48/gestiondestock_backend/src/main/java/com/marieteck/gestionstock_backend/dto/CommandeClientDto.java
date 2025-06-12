package com.marieteck.gestionstock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marieteck.gestionstock_backend.model.Client;
import com.marieteck.gestionstock_backend.model.CommandeClient;
import com.marieteck.gestionstock_backend.model.EtatCommande;
import com.marieteck.gestionstock_backend.model.LigneCommandeClient;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {

    private Long id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private ClientDto clientDto;

    @JsonIgnore
    private List<LigneCommandeClientDto> ligneCommandeClients;

     //FromEntity

    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if (commandeClient == null) {
            return null;
        }
       return CommandeClientDto.builder()
               .id(commandeClient.getId())
               .code(commandeClient.getCode())
               .dateCommande(commandeClient.getDateCommande())
               .clientDto(ClientDto.fromEntity(commandeClient.getClient()))
               .build();

    }

    // ToEntity
    public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {
        if (commandeClientDto== null) {
            return null;
        }
       CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());
        commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClientDto()));

        return commandeClient;

    }
}
