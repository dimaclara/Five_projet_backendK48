package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.auth.TokenRequest;
import com.marieteck.gestionstock_backend.dto.auth.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Admin Token Management", description = "API pour la gestion des tokens d'identification par les administrateurs")
public interface AdminTokenApi {

    @Operation(
            summary = "Créer un nouveau token d'identification",
            description = "Génère un token d'identification pour une entreprise ou un client et l'envoie par email"
    )
    @ApiResponse(responseCode = "200", description = "Token créé avec succès")
    @PostMapping("/admin/tokens")
    ResponseEntity<TokenResponse> createToken(@RequestBody TokenRequest request);

    @Operation(
            summary = "Lister tous les tokens actifs",
            description = "Récupère la liste de tous les tokens d'identification actifs"
    )
    @GetMapping("/admin/tokens")
    ResponseEntity<List<TokenResponse>> getAllActiveTokens();

    @Operation(
            summary = "Lister les tokens d'une entreprise",
            description = "Récupère tous les tokens associés à une entreprise spécifique"
    )
    @GetMapping("/admin/tokens/enterprise/{enterpriseId}")
    ResponseEntity<List<TokenResponse>> getTokensByEnterprise(
            @Parameter(description = "ID de l'entreprise") @PathVariable Long enterpriseId
    );

    @Operation(
            summary = "Lister les tokens créés par un admin",
            description = "Récupère tous les tokens créés par un administrateur spécifique"
    )
    @GetMapping("/admin/tokens/created-by/{adminId}")
    ResponseEntity<List<TokenResponse>> getTokensCreatedByAdmin(
            @Parameter(description = "ID de l'administrateur") @PathVariable Long adminId
    );

    @Operation(
            summary = "Désactiver un token",
            description = "Désactive un token d'identification spécifique"
    )
    @ApiResponse(responseCode = "200", description = "Token désactivé avec succès")
    @DeleteMapping("/admin/tokens/{tokenId}")
    ResponseEntity<Void> deactivateToken(
            @Parameter(description = "ID du token à désactiver") @PathVariable Long tokenId
    );

    @Operation(
            summary = "Renouveler un token",
            description = "Renouvelle un token existant avec une nouvelle date d'expiration"
    )
    @ApiResponse(responseCode = "200", description = "Token renouvelé avec succès")
    @PutMapping("/admin/tokens/{tokenId}/renew")
    ResponseEntity<TokenResponse> renewToken(
            @Parameter(description = "ID du token à renouveler") @PathVariable Long tokenId,
            @Parameter(description = "Nouvelle durée en jours") @RequestParam Integer expirationDays
    );

    @Operation(
            summary = "Nettoyer les tokens expirés",
            description = "Désactive automatiquement tous les tokens expirés"
    )
    @ApiResponse(responseCode = "200", description = "Nettoyage effectué avec succès")
    @PostMapping("/admin/tokens/cleanup")
    ResponseEntity<Void> cleanupExpiredTokens();

    @Operation(
            summary = "Renvoyer un token par email",
            description = "Renvoie un token existant par email à l'utilisateur"
    )
    @ApiResponse(responseCode = "200", description = "Email envoyé avec succès")
    @PostMapping("/admin/tokens/{tokenId}/resend")
    ResponseEntity<Void> resendToken(
            @Parameter(description = "ID du token à renvoyer") @PathVariable Long tokenId
    );

    @Operation(
            summary = "Vérifier un token",
            description = "Vérifie la validité d'un token d'identification"
    )
    @GetMapping("/admin/tokens/verify/{token}")
    ResponseEntity<TokenResponse> verifyToken(
            @Parameter(description = "Token à vérifier") @PathVariable String token
    );
}
