package com.marieteck.gestionstock_backend.controller;


import com.marieteck.gestionstock_backend.dto.auth.TokenRequest;
import com.marieteck.gestionstock_backend.dto.auth.TokenResponse;
import com.marieteck.gestionstock_backend.exception.EntityNotFoundException;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.model.AuthToken;
import com.marieteck.gestionstock_backend.model.Users;
import com.marieteck.gestionstock_backend.repository.UsersRepository;
import com.marieteck.gestionstock_backend.service.AuthTokenService;
import com.marieteck.gestionstock_backend.service.UsersServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AdminTokenController {

    private final AuthTokenService authTokenService;
    private final UsersServices usersService;
    private final UsersRepository usersRepository;

//    @Override
    public ResponseEntity<TokenResponse> createToken(TokenRequest request) {
        log.info("Création d'un nouveau token pour: {}", request.getUserEmail());

        Users currentAdmin = getCurrentUser();
        TokenResponse tokenResponse = authTokenService.createToken(request, currentAdmin);

        log.info("Token créé avec succès: {}", tokenResponse.getId());
        return ResponseEntity.ok(tokenResponse);
    }

//    @Override
    public ResponseEntity<List<TokenResponse>> getAllActiveTokens() {
        log.info("Récupération de tous les tokens actifs");

        List<TokenResponse> tokens = authTokenService.getAllActiveTokens();
        return ResponseEntity.ok(tokens);
    }

//    @Override
    public ResponseEntity<List<TokenResponse>> getTokensByEnterprise(Long enterpriseId) {
        log.info("Récupération des tokens pour l'entreprise: {}", enterpriseId);

        List<TokenResponse> tokens = authTokenService.getTokensByEnterprise(enterpriseId);
        return ResponseEntity.ok(tokens);
    }

//    @Override
    public ResponseEntity<List<TokenResponse>> getTokensCreatedByAdmin(Long adminId) {
        log.info("Récupération des tokens créés par l'admin: {}", adminId);

        List<TokenResponse> tokens = authTokenService.getTokensCreatedByAdmin(adminId);
        return ResponseEntity.ok(tokens);
    }

//    @Override
    public ResponseEntity<Void> deactivateToken(Long tokenId) {
        log.info("Désactivation du token: {}", tokenId);

        Users currentAdmin = getCurrentUser();
        authTokenService.deactivateToken(tokenId, currentAdmin);

        log.info("Token {} désactivé avec succès", tokenId);
        return ResponseEntity.ok().build();
    }

//    @Override
    public ResponseEntity<TokenResponse> renewToken(Long tokenId, Integer expirationDays) {
        log.info("Renouvellement du token: {} pour {} jours", tokenId, expirationDays);

        Users currentAdmin = getCurrentUser();
        TokenResponse tokenResponse = authTokenService.renewToken(tokenId, expirationDays, currentAdmin);

        log.info("Token {} renouvelé avec succès", tokenId);
        return ResponseEntity.ok(tokenResponse);
    }

//    @Override
    public ResponseEntity<Void> cleanupExpiredTokens() {
        log.info("Nettoyage des tokens expirés");

        authTokenService.cleanupExpiredTokens();

        log.info("Nettoyage des tokens expirés terminé");
        return ResponseEntity.ok().build();
    }

//    @Override
    public ResponseEntity<Void> resendToken(Long tokenId) {
        log.info("Renvoi du token: {}", tokenId);

        // Récupérer le token et le renvoyer par email
        List<TokenResponse> allTokens = authTokenService.getAllActiveTokens();
        TokenResponse token = allTokens.stream()
                .filter(t -> t.getId().equals(tokenId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Token introuvable"));

        authTokenService.sendTokenByEmail(token);

        log.info("Token {} renvoyé avec succès", tokenId);
        return ResponseEntity.ok().build();
    }

//    @Override
    public ResponseEntity<TokenResponse> verifyToken(String token) {
        log.info("Vérification du token: {}", token.substring(0, Math.min(token.length(), 10)) + "...");

        AuthToken authToken = authTokenService.validateToken(token);
        TokenResponse response = TokenResponse.fromEntity(authToken);

        log.info("Token vérifié avec succès pour l'utilisateur: {}", authToken.getUser().getEmail());
        return ResponseEntity.ok(response);
    }

    private Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return usersRepository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException("user not found with this email : " + email, ErrorCodes.USERS_NOT_FOUND));
    }
}
