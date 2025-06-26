package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.VentesApi;
import com.marieteck.gestionstock_backend.dto.VentesDto;
import com.marieteck.gestionstock_backend.service.VentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VentesController implements VentesApi {


    private final VentesService ventesService;
    @Autowired
    public VentesController(VentesService ventesService) {
        this.ventesService = ventesService;
    }


    @Override
    public VentesDto save(VentesDto ventesDto) {
        return ventesService.save(ventesDto);
    }

    @Override
    public VentesDto findById(Long id) {
        return ventesService.findById(id);
    }

    @Override
    public VentesDto findByCode(String code) {
        return ventesService.findByCode(code);
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        ventesService.deleteById(id);

    }
}
