package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.EnterpriseApi;
import com.marieteck.gestionstock_backend.dto.EnterpriseDto;
import com.marieteck.gestionstock_backend.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EnterpriseController implements EnterpriseApi {

    private final EnterpriseService enterpriseService;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService){
        this.enterpriseService = enterpriseService;
    }

    @Override
    public EnterpriseDto save(EnterpriseDto enterpriseDto) {
        return enterpriseService.save(enterpriseDto);
    }

    @Override
    public EnterpriseDto findById(Long id) {
        return enterpriseService.findById(id);
    }

    @Override
    public List<EnterpriseDto> findAll() {
        return enterpriseService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        enterpriseService.deleteById(id);

    }
}
