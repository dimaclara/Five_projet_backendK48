package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.ClientDto;
import com.marieteck.gestionstock_backend.exception.EntityNotFoundException;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.model.Client;
import com.marieteck.gestionstock_backend.repository.ClientRepository;
import com.marieteck.gestionstock_backend.service.ClientService;
import com.marieteck.gestionstock_backend.validator.ClientValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors = ClientValidator.validate(clientDto);
        if(errors.isEmpty()){
            log.error("client is not valid {}", clientDto);
           throw new InvalidEntityException("client is not available", ErrorCodes.ARTICLE_NOT_FOUND,errors);
        }
        Client clientSaved = clientRepository.save(ClientDto.toEntity(clientDto));
        return ClientDto.fromEntity(clientSaved);
    }

    @Override
    public ClientDto findById(Long id) {
        if(id == null){
            log.error("id is null");

        }
        return clientRepository.findById(id)
                .map(ClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("not category has this " + id, ErrorCodes.CATEGORY_ALREADY_IN_USE));
    }

    @Override
    public List<ClientDto> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {
        return;
    }
}
