package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.PexelApi;
import com.marieteck.gestionstock_backend.service.PexelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PexelController implements PexelApi {


    private final PexelService pexelService;
    @Autowired
    public PexelController(PexelService pexelService) {
        this.pexelService = pexelService;
    }

    @Override
    public String savePhoto(String keyword) {
        return pexelService.savePhoto(keyword);
    }
}
