package com.marieteck.gestionstock_backend.dto.auth;

import com.marieteck.gestionstock_backend.model.AbstractEntity;
import com.marieteck.gestionstock_backend.model.AuthToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse extends AbstractEntity {


    private String token;
    private String userEmail;
    private String userName;
    private String enterpriseName;
    private AuthToken.TokenType tokenType;
    private String purpose;
    private Instant expiresAt;
    private Boolean isActive;
    private Instant lastUsed;
    private String createdByEmail;
    private Instant createdAt;

    public static TokenResponse fromEntity(AuthToken authToken) {
        TokenResponse response = new TokenResponse();
        response.setId(authToken.getId());
        response.setToken(authToken.getToken());
        response.setUserEmail(authToken.getUser().getEmail());
        response.setUserName(authToken.getUser().getNom() + " " + authToken.getUser().getPrenom());
        response.setEnterpriseName(authToken.getEnterprise() != null ? authToken.getEnterprise().getNom() : null);
        response.setTokenType(authToken.getTokenType());
        response.setPurpose(authToken.getPurpose());
        response.setExpiresAt(authToken.getExpiresAt());
        response.setIsActive(authToken.getIsActive());
        response.setLastUsed(authToken.getLastUsed());
        response.setCreatedByEmail(authToken.getCreatedBy().getEmail());
        response.setCreatedAt(authToken.getCreationDate());
        return response;
    }
}
