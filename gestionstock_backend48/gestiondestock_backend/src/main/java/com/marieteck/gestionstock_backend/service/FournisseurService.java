package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto dto);

    FournisseurDto findById(Long id);

    List<FournisseurDto> findAll();

    void deleteById(Long id);
}
