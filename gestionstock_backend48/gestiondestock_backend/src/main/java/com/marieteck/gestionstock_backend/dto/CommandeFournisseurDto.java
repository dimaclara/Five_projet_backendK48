package com.marieteck.gestionstock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marieteck.gestionstock_backend.model.CommandeFournisseur;
import com.marieteck.gestionstock_backend.model.EtatCommande;
import com.marieteck.gestionstock_backend.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;


@Data
@Builder

public class CommandeFournisseurDto {

    private Long id;

    private String code;

    private Instant dateCommande;

    private FournisseurDto fournisseurDto;

    private EtatCommande etatCommande;

    @JsonIgnore
    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;


    // FromEntity
    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
        if (commandeFournisseur == null){
            return null;
        }
        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .fournisseurDto(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))


                .build();

    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto) {
        if (commandeFournisseurDto == null){
            return null;
            //TODO Throw an exception

        }
        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(commandeFournisseurDto.getId());
        commandeFournisseur.setCode(commandeFournisseurDto.getCode());
        commandeFournisseur.setDateCommande(commandeFournisseurDto.getDateCommande());
        commandeFournisseur.setFournisseur(FournisseurDto.toEntity(commandeFournisseurDto.getFournisseurDto()));

        return commandeFournisseur;
    }

}
