package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.CommandeClientApi;
import com.marieteck.gestionstock_backend.dto.CommandeClientDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        return null;
    }

    @Override
    public CommandeClientDto findById(Long id) {
        return null;
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        return null;
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }
}
