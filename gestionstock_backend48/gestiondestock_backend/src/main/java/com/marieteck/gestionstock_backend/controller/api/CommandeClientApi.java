package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.CommandeClientDto;
import com.marieteck.gestionstock_backend.dto.LigneCommandeClientDto;

import java.util.List;

public interface CommandeClientApi {
    CommandeClientDto save(CommandeClientDto commandeClientDto);
    CommandeClientDto findById(Long id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();



    void deleteById(Long id);


}
