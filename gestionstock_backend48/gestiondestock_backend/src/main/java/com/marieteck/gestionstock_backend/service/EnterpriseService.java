package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.EnterpriseDto;

import java.util.List;

public interface EnterpriseService {

    EnterpriseDto save(EnterpriseDto dto);

    EnterpriseDto findById(Long id);

    List<EnterpriseDto> findAll();

    void deleteById(Long id);
}
