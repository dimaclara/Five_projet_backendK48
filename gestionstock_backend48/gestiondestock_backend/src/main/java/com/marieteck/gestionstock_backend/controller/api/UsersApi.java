package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.UsersDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;

public interface UsersApi {

    @PostMapping(value = APP_ROOT + "/users/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UsersDto save(@RequestBody UsersDto usersDto);

    @GetMapping(value = APP_ROOT + "/users/{idUsers}", produces = MediaType.APPLICATION_JSON_VALUE)
    UsersDto findById(@PathVariable("idUsers") Long id);

    @GetMapping(value = APP_ROOT + "/users/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UsersDto> findAll();

    @GetMapping(value = APP_ROOT + "/users/delete/{idUsers}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteById(@PathVariable("idUsers") Long id);

}
