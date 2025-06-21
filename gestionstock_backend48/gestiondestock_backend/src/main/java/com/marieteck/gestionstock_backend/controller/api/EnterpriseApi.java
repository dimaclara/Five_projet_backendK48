package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.EnterpriseDto;

import java.util.List;

public interface EnterpriseApi {

    EnterpriseDto save(EnterpriseDto dto);

    EnterpriseDto findById(Long id);

    List<EnterpriseDto> findAll();

    void deleteById(Long id);
}
