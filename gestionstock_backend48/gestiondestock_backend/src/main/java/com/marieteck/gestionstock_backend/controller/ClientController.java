package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.ClientApi;
import com.marieteck.gestionstock_backend.dto.ClientDto;
import com.marieteck.gestionstock_backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientController implements ClientApi {

    @Autowired
    private ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        return clientService.save(clientDto);
    }

    @Override
    public ClientDto findById(Long id) {
        return clientService.findById(id);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        clientService.deleteById(id);

    }
}
