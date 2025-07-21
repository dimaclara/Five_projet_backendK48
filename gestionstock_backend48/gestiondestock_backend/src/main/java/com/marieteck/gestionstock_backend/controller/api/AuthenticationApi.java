package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.auth.AuthenticationRequest;
import com.marieteck.gestionstock_backend.dto.auth.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface AuthenticationApi {
    @PostMapping( "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
}
