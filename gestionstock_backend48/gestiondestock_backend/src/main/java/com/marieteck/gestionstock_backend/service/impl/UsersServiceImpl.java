package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.UsersDto;
import com.marieteck.gestionstock_backend.model.Users;
import com.marieteck.gestionstock_backend.repository.UsersRepository;
import com.marieteck.gestionstock_backend.service.UsersServices;
import com.marieteck.gestionstock_backend.validator.UsersValidators;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor

public class UsersServiceImpl implements UsersServices {

    private final UsersRepository usersRepository;


    @Override
    public UsersDto save(UsersDto usersDto) {
        List<String> errors = UsersValidators.validate(usersDto);
        if (errors == null) {
            log.error("Users is not valid");
            return null;
        }
        // Verification de la logique metier

        Users usersSaved = usersRepository.save(UsersDto.toEntity(usersDto));
        return UsersDto.fromEntity(usersSaved);
    }

    @Override
    public UsersDto findById(Long id) {
        return null;
    }

    @Override
    public List<UsersDto> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public UsersDto findByEmail(String email) {
        return null;
    }
}
