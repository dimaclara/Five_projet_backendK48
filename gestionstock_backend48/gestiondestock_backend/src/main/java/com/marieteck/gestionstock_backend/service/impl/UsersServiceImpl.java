package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.UsersDto;
import com.marieteck.gestionstock_backend.exception.EntityNotFoundException;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.model.Users;
import com.marieteck.gestionstock_backend.repository.UsersRepository;
import com.marieteck.gestionstock_backend.service.UsersServices;
import com.marieteck.gestionstock_backend.validator.UsersValidators;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

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
        if (id == null) {
            log.error("Id is null");
            return null;

        }
        return usersRepository.findById(id)
                .map(UsersDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Not user with this id " + id , ErrorCodes.USERS_NOT_FOUND
                ));
    }

    @Override
    public List<UsersDto> findAll() {

        return usersRepository.findAll().stream()
                .map(UsersDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("Id is null");
            return ;

        }
        usersRepository.deleteById(id);

    }

    @Override
    public UsersDto findByEmail(String email) {
        if (!StringUtils.hasLength(email) ) {
            log.error("Email is empty");
            return null;

        }
        return usersRepository.findByEmail(email)
                .map(UsersDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "no user found with this email  " + email + "in the DB" , ErrorCodes.USERS_NOT_FOUND
                ));
    }
}
