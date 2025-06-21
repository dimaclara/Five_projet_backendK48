package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.ClientDto;

import java.util.List;

public interface ClientApi {
    ClientDto save(ClientDto clientDto);

    ClientDto findById(Long id);

    List<ClientDto> findAll();

    void deleteById(Long id);
}
