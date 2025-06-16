package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.VentesDto;

import java.util.List;

public interface VentesService {

     VentesDto save(VentesDto ventesDto);

     VentesDto findById(Long id);

    VentesDto findByCode(String code);

    List<VentesDto> findAll();

    void deleteById(Long id);
}
