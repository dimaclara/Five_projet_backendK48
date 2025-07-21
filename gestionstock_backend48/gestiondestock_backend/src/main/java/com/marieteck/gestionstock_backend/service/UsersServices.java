package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.EnterpriseDto;
import com.marieteck.gestionstock_backend.dto.UsersDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersServices {

    UsersDto save(UsersDto usersDto);

    UsersDto findById(Long id);

    List<UsersDto> findAll();

    void deleteById(Long id);


    UsersDto findByEmail(String email);

    //UsersDto ChangePassword(ChangePassWordUsersDto changePassWordUsersDto);

}
