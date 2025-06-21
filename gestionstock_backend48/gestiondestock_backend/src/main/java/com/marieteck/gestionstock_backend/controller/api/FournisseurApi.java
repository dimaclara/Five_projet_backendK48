package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.FournisseurDto;

import java.util.List;

public interface FournisseurApi {
    FournisseurDto save(FournisseurDto dto);

    FournisseurDto findById(Long id);

    List<FournisseurDto> findAll();

    void deleteById(Long id);
}
