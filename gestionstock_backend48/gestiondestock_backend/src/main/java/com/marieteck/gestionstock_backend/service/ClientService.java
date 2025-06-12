package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    ClientDto findById(Long id);

    List<ClientDto> findAll();

    void deleteById(Long id);
}
