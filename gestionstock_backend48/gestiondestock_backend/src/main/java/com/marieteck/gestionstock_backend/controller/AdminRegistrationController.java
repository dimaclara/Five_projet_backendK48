package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.AdminRegistrationApi;
import com.marieteck.gestionstock_backend.dto.auth.AdminRegistrationRequest;
import com.marieteck.gestionstock_backend.dto.auth.AdminRegistrationResponse;
import com.marieteck.gestionstock_backend.service.AdminRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth/admin")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class AdminRegistrationController implements AdminRegistrationApi {
    private final AdminRegistrationService adminRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<AdminRegistrationResponse> registerAdmin(@RequestBody AdminRegistrationRequest request) {
        log.info("Demande d'enregistrement admin reçue pour: {}", request.getEmail());

        AdminRegistrationResponse response = adminRegistrationService.registerAdmin(request);

        log.info("Admin enregistré avec succès: {}", response.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailAvailability(@RequestParam String email) {
        log.info("Vérification de la disponibilité de l'email: {}", email);

        boolean isEmailUsed = adminRegistrationService.isEmailAlreadyUsed(email);

        return ResponseEntity.ok(!isEmailUsed); // true si disponible, false si déjà utilisé
    }

    @GetMapping("/validate-email")
    public ResponseEntity<Boolean> validateEmailFormat(@RequestParam String email) {
        log.info("Validation du format email: {}", email);

        boolean isValid = adminRegistrationService.isValidEmail(email);

        return ResponseEntity.ok(isValid);
    }





}
