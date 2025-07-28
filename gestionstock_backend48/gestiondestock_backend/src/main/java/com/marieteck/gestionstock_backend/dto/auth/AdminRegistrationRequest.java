package com.marieteck.gestionstock_backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegistrationRequest {

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String confirmMotDePasse;

    // Informations optionnelles de l'entreprise
    private String enterpriseName;
    private String enterpriseEmail;
    private String enterpriseDescription;
    private String enterprisePhone;
    private String enterpriseAddress;
}
