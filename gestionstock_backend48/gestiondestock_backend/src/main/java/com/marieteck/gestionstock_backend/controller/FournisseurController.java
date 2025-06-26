package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.FournisseurApi;
import com.marieteck.gestionstock_backend.dto.FournisseurDto;
import com.marieteck.gestionstock_backend.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {

    private final FournisseurService fournisseurService;
    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        return fournisseurService.save(fournisseurDto);
    }

    @Override
    public FournisseurDto findById(Long id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        fournisseurService.deleteById(id);

    }
}
