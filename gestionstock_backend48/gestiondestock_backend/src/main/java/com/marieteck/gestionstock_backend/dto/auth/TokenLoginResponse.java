package com.marieteck.gestionstock_backend.dto.auth;

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
public class TokenLoginResponse {
    private String accessToken; // JWT token généré
    private Long userId;
    private String userEmail;
    private String userName;
    private String enterpriseName;
    private AuthToken.TokenType tokenType;
    private Instant tokenExpiresAt;
    private String message;
}
