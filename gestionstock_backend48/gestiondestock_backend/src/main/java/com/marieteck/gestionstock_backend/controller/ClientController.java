package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.ClientApi;
import com.marieteck.gestionstock_backend.dto.ClientDto;
import com.marieteck.gestionstock_backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@RestController
public class ClientController implements ClientApi {


    private final ClientService clientService;
    @Autowired
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
