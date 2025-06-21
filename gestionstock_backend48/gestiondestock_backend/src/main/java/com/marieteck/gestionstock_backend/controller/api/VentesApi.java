package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.VentesDto;

import java.util.List;

public interface VentesApi {
    VentesDto save(VentesDto ventesDto);

    VentesDto findById(Long id);

    VentesDto findByCode(String code);

    List<VentesDto> findAll();

    void deleteById(Long id);
}
