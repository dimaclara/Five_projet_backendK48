package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.auth.TokenRequest;
import com.marieteck.gestionstock_backend.dto.auth.TokenResponse;
import com.marieteck.gestionstock_backend.model.AuthToken;
import com.marieteck.gestionstock_backend.model.Users;

import java.util.List;

public interface AuthTokenService {
    /**
     * Créer un nouveau token d'identification pour un utilisateur/entreprise
     */
    TokenResponse createToken(TokenRequest request, Users admin);

    /**
     * Valider un token d'identification
     */
    AuthToken validateToken(String token);

    /**
     * Récupérer tous les tokens actifs
     */
    List<TokenResponse> getAllActiveTokens();

    /**
     * Récupérer les tokens d'une entreprise
     */
    List<TokenResponse> getTokensByEnterprise(Long enterpriseId);

    /**
     * Récupérer les tokens créés par un admin
     */
    List<TokenResponse> getTokensCreatedByAdmin(Long adminId);

    /**
     * Désactiver un token
     */
    void deactivateToken(Long tokenId, Users admin);

    /**
     * Supprimer les tokens expirés
     */
    void cleanupExpiredTokens();

    /**
     * Envoyer le token par email à l'utilisateur
     */
    void sendTokenByEmail(TokenResponse tokenResponse);

    /**
     * Mettre à jour la dernière utilisation d'un token
     */
    void updateLastUsed(String token);

    /**
     * Vérifier si un utilisateur peut accéder à une ressource
     */
    boolean canAccessResource(String token, String resource, String action);

    /**
     * Renouveler un token existant
     */
    TokenResponse renewToken(Long tokenId, Integer newExpirationDays, Users admin);
}
