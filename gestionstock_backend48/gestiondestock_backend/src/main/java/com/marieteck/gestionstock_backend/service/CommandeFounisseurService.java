package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.CommandeFournisseurDto;

import java.util.List;

public interface CommandeFounisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto);

    List<CommandeFournisseurDto> findAll();

    CommandeFournisseurDto findById(Long id);

    CommandeFournisseurDto findByCode(String code);

    void deleteById(Long id);

}
