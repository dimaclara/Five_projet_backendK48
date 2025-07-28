package com.marieteck.gestionstock_backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegistrationResponse {

    private Long adminId;
    private String email;
    private String nom;
    private String prenom;
    private String message;
    private String accessToken; // JWT token pour connexion immédiate
    private Long enterpriseId;
    private String enterpriseName;
}
