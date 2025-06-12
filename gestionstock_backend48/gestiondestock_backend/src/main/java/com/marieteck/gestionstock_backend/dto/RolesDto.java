package com.marieteck.gestionstock_backend.dto;

import com.marieteck.gestionstock_backend.model.Roles;
import com.marieteck.gestionstock_backend.model.Users;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class RolesDto {

    private Long id;

    private String roleName;

    private UsersDto usersDto;

    public static RolesDto fromEntity(Roles roles) {
        // condition
        if (roles == null) {
            return null;
            //TODO Throw an exception


        }
        return RolesDto.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .usersDto(UsersDto.fromEntity(roles.getUsers()))

                .build();
    }

    // ToEntity

    public static Roles toEntity(RolesDto rolesDto) {
        //Condition
        if (rolesDto == null) {
            return null;
        }
        Roles roles = new Roles();
        roles.setId(rolesDto.getId());
        roles.setRoleName(rolesDto.getRoleName());
        roles.setUsers(UsersDto.toEntity(rolesDto.getUsersDto()));
        return roles;
    }
}


