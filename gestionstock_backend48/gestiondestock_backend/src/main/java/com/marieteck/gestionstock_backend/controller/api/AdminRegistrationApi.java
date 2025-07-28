package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.auth.AdminRegistrationRequest;
import com.marieteck.gestionstock_backend.dto.auth.AdminRegistrationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AdminRegistrationApi {
    @Operation(summary = "Enregistrer un nouvel administrateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Admin créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "409", description = "Email déjà utilisé")
    })
    @PostMapping("/register")
    ResponseEntity<AdminRegistrationResponse> registerAdmin(@RequestBody AdminRegistrationRequest request);

    @Operation(summary = "Vérifier la disponibilité d'un email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Résultat de la vérification")
    })
    @GetMapping("/check-email")
    ResponseEntity<Boolean> checkEmailAvailability(@RequestParam String email);

    @Operation(summary = "Valider le format d'un email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Résultat de la validation")
    })
    @GetMapping("/validate-email")
    ResponseEntity<Boolean> validateEmailFormat(@RequestParam String email);


}
