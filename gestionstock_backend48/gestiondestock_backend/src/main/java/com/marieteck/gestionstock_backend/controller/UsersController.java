package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.UsersApi;
import com.marieteck.gestionstock_backend.dto.UsersDto;
import com.marieteck.gestionstock_backend.service.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController implements UsersApi {

    @Autowired
    private UsersServices usersServices;
    public UsersController(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @Override
    public UsersDto save(UsersDto usersDto) {
        return usersServices.save(usersDto);
    }

    @Override
    public UsersDto findById(Long id) {
        return usersServices.findById(id);
    }

    @Override
    public List<UsersDto> findAll() {
        return usersServices.findAll();
    }

    @Override
    public void deleteById(Long id) {
        usersServices.deleteById(id);

    }
}
