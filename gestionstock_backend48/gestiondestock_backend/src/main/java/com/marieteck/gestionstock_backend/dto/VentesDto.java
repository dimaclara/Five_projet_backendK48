package com.marieteck.gestionstock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marieteck.gestionstock_backend.model.LigneVente;
import com.marieteck.gestionstock_backend.model.Ventes;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Builder
@Data
public class VentesDto {

    private Long id;

    private String code;

    private Instant dateVente;

    private String commentaire;

    @JsonIgnore
    private List<LigneVenteDto> ligneVentes;

    // FromEntity

    public static VentesDto fromEntity(Ventes ventes) {
        //Condition
        if (ventes == null) {
            return null;
            //TODO Throws an exception

        }
        return VentesDto.builder()
                .id(ventes.getId())
                .code(ventes.getCode())
                .commentaire(ventes.getCommentaire())
                .dateVente(ventes.getDateVente())
                .build();
    }

    // ToEntity
    public static Ventes toEntity(VentesDto ventesDto) {
        if (ventesDto == null) {
            return null;

        }
        Ventes ventes = new Ventes();
        ventes.setId(ventesDto.getId());
        ventes.setCode(ventesDto.getCode());
        ventes.setCommentaire(ventesDto.getCommentaire());
        ventes.setDateVente(ventesDto.getDateVente());
        return ventes;


    }

}
