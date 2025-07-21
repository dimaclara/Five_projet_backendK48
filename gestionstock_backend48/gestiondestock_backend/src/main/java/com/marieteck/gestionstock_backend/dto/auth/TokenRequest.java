package com.marieteck.gestionstock_backend.dto.auth;

import com.marieteck.gestionstock_backend.model.AuthToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequest {
    private String userEmail;
    private String userName;
    private String userPrenom;
    private Long enterpriseId;
    private AuthToken.TokenType tokenType;
    private String purpose;
    private Long expirationDays ;
    private String ipRestrictions;

    // Pour créer une nouvelle entreprise avec le token
    private String enterpriseName;
    private String enterpriseEmail;
    private String enterpriseDescription;
    private String enterprisePhone;
}
