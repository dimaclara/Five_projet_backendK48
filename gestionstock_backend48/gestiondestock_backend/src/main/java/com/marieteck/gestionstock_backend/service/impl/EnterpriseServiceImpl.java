package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.EnterpriseDto;
import com.marieteck.gestionstock_backend.exception.EntityNotFoundException;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.repository.EnterpriseRepository;
import com.marieteck.gestionstock_backend.repository.RolesRepository;
import com.marieteck.gestionstock_backend.repository.UsersRepository;
import com.marieteck.gestionstock_backend.service.EnterpriseService;
import com.marieteck.gestionstock_backend.validator.EnterpriseValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EnterpriseServiceImpl implements EnterpriseService {

    private EnterpriseRepository enterpriseRepository;
    private UsersRepository usersRepository;
    private RolesRepository rolesRepository;

    @Override
    public EnterpriseDto save(EnterpriseDto enterpriseDto) {

        // validation d
        List<String> errors = EnterpriseValidator.validate(enterpriseDto);
        if (errors == null) {
            log.error("Enterprise is not valide");
            throw new InvalidEntityException("Enterprise is not valide", ErrorCodes.ENTERPRISE_NOT_VALID, errors);

        }

        EnterpriseDto savedEntreprise = EnterpriseDto.fromEntity(
                enterpriseRepository.save(EnterpriseDto.toEntity(enterpriseDto))
        );

        return  savedEntreprise;

    }


    @Override
    public EnterpriseDto findById(Long id) {
        if (id == null) {
            log.error("Enterprise id is null");
            return null;
        }

        return enterpriseRepository.findById(id)
                .map(EnterpriseDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Enterprise with id " + id + " not found",ErrorCodes.ENTERPRISE_NOT_FOUND
                ));
    }

    @Override
    public List<EnterpriseDto> findAll() {
        return enterpriseRepository.findAll().stream()
                .map(EnterpriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("Enterprise id is null");
            return;

        }
        enterpriseRepository.deleteById(id);
    }
}

