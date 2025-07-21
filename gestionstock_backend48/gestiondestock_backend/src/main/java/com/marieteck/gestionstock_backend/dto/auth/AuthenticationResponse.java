package com.marieteck.gestionstock_backend.dto.auth;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AuthenticationResponse {
    private String accessToken;
}
