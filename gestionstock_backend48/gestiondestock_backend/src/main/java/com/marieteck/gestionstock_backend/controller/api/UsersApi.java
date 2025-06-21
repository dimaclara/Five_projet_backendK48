package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.UsersDto;

import java.util.List;

public interface UsersApi {

    UsersDto save(UsersDto usersDto);

    UsersDto findById(Long id);

    List<UsersDto> findAll();

    void deleteById(Long id);

}
