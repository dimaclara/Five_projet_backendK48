package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.AuthenticationApi;
import com.marieteck.gestionstock_backend.dto.auth.AuthenticationRequest;
import com.marieteck.gestionstock_backend.dto.auth.AuthenticationResponse;
import com.marieteck.gestionstock_backend.dto.auth.ExtendedUser;
import com.marieteck.gestionstock_backend.service.auth.CustomUserDetailsService;
import com.marieteck.gestionstock_backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements AuthenticationApi {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService  userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());

        final String jwt = jwtUtils.generateToken((ExtendedUser) userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }


}
