package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.auth.TokenRequest;
import com.marieteck.gestionstock_backend.dto.auth.TokenResponse;
import com.marieteck.gestionstock_backend.exception.EntityNotFoundException;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.exception.InvalidOperationException;
import com.marieteck.gestionstock_backend.model.AuthToken;
import com.marieteck.gestionstock_backend.model.Enterprise;
import com.marieteck.gestionstock_backend.model.Roles;
import com.marieteck.gestionstock_backend.model.Users;
import com.marieteck.gestionstock_backend.repository.AuthTokenRepository;
import com.marieteck.gestionstock_backend.repository.EnterpriseRepository;
import com.marieteck.gestionstock_backend.repository.RolesRepository;
import com.marieteck.gestionstock_backend.repository.UsersRepository;
import com.marieteck.gestionstock_backend.service.AuthTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.marieteck.gestionstock_backend.utils.Constants;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthTokenServiceImpl implements AuthTokenService {

    @Autowired
    private  AuthTokenRepository authTokenRepository;

    @Autowired
    private  UsersRepository usersRepository;

    @Autowired
    private  EnterpriseRepository enterpriseRepository;

    @Autowired
    private  RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String TOKEN_PREFIX = "tk_";
    private static final SecureRandom secureRandom = new SecureRandom();

    @Override
    public TokenResponse createToken(TokenRequest request, Users admin) {
        validateTokenRequest(request);
        validateAdminPermissions(admin);

        // Créer ou récupérer l'entreprise
        Enterprise enterprise = null;
        if (request.getEnterpriseId() != null) {
            enterprise = enterpriseRepository.findById(request.getEnterpriseId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Entreprise introuvable avec l'ID : " + request.getEnterpriseId(),
                            ErrorCodes.ENTERPRISE_NOT_FOUND));
        } else if (request.getEnterpriseName() != null && !request.getEnterpriseName().trim().isEmpty()) {
            enterprise = createNewEnterprise(request);
        }

        // Créer ou récupérer l'utilisateur
        Users user = createOrUpdateUser(request, enterprise);

        // Générer le token
        String token = generateSecureToken();

        // Créer l'AuthToken
        AuthToken authToken = new AuthToken();
        authToken.setToken(token);
        authToken.setUser(user);
        authToken.setEnterprise(enterprise);
        authToken.setTokenType(request.getTokenType());
        authToken.setPurpose(request.getPurpose());
        authToken.setExpiresAt(Instant.now().plus(request.getExpirationDays(), ChronoUnit.DAYS));
        authToken.setIsActive(true);
        authToken.setCreatedBy(admin);
        authToken.setIpRestrictions(request.getIpRestrictions());

        authToken = authTokenRepository.save(authToken);

        TokenResponse response = TokenResponse.fromEntity(authToken);

        // Envoyer le token par email
        try {
            sendTokenByEmail(response);
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi de l'email pour le token: {}", token, e);
        }

        log.info("Token créé avec succès pour l'utilisateur: {} par l'admin: {}",
                user.getEmail(), admin.getEmail());

        return response;
    }

    @Override
    public AuthToken validateToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new InvalidEntityException("Token ne peut pas être vide", ErrorCodes.INVALID_ENTITY);
        }

        Optional<AuthToken> authTokenOpt = authTokenRepository.findByTokenAndIsActiveTrue(token);

        if (authTokenOpt.isEmpty()) {
            throw new EntityNotFoundException("Token invalide ou inactif", ErrorCodes.TOKEN_NOT_FOUND);
        }

        AuthToken authToken = authTokenOpt.get();

        if (authToken.isExpired()) {
            authToken.setIsActive(false);
            authTokenRepository.save(authToken);
            throw new InvalidOperationException("Token expiré", ErrorCodes.TOKEN_EXPIRED);
        }

        return authToken;
    }

    @Override
    public List<TokenResponse> getAllActiveTokens() {
        List<AuthToken> activeTokens = authTokenRepository.findActiveTokens(Instant.now());
        return activeTokens.stream()
                .map(TokenResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<TokenResponse> getTokensByEnterprise(Long enterpriseId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Entreprise introuvable", ErrorCodes.ENTERPRISE_NOT_FOUND));

        List<AuthToken> tokens = authTokenRepository.findByEnterpriseAndIsActiveTrue(enterprise);
        return tokens.stream()
                .map(TokenResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<TokenResponse> getTokensCreatedByAdmin(Long adminId) {
        Users admin = usersRepository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Admin introuvable", ErrorCodes.USER_NOT_FOUND));

        List<AuthToken> tokens = authTokenRepository.findTokensCreatedByAdmin(admin);
        return tokens.stream()
                .map(TokenResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deactivateToken(Long tokenId, Users admin) {
        validateAdminPermissions(admin);

        AuthToken authToken = authTokenRepository.findById(tokenId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Token introuvable", ErrorCodes.TOKEN_NOT_FOUND));

        authToken.setIsActive(false);
        authTokenRepository.save(authToken);

        log.info("Token {} désactivé par l'admin: {}", tokenId, admin.getEmail());
    }

    @Override
    public void cleanupExpiredTokens() {
        List<AuthToken> expiredTokens = authTokenRepository.findExpiredTokens(Instant.now());

        for (AuthToken token : expiredTokens) {
            token.setIsActive(false);
        }

        authTokenRepository.saveAll(expiredTokens);
        log.info("Nettoyage effectué: {} tokens expirés désactivés", expiredTokens.size());
    }

    @Override
    public void sendTokenByEmail(TokenResponse tokenResponse) {
        // TODO: Implémenter l'envoi d'email avec le service de mail
        // Pour l'instant, on logue les informations
        log.info("=== TOKEN D'IDENTIFICATION GÉNÉRÉ ===");
        log.info("Destinataire: {}", tokenResponse.getUserEmail());
        log.info("Nom: {}", tokenResponse.getUserName());
        log.info("Entreprise: {}", tokenResponse.getEnterpriseName());
        log.info("Token: {}", tokenResponse.getToken());
        log.info("Type: {}", tokenResponse.getTokenType());
        log.info("Expire le: {}", tokenResponse.getExpiresAt());
        log.info("Objet: {}", tokenResponse.getPurpose());
        log.info("=====================================");
    }

    @Override
    public void updateLastUsed(String token) {
        AuthToken authToken = validateToken(token);
        authToken.setLastUsed(Instant.now());
        authTokenRepository.save(authToken);
    }

    @Override
    public boolean canAccessResource(String token, String resource, String action) {
        try {
            AuthToken authToken = validateToken(token);

            // Logique de permissions basée sur le type de token
            switch (authToken.getTokenType()) {
                case FULL_ACCESS:
                    return true;
                case READ_ONLY:
                    return "GET".equalsIgnoreCase(action) || "read".equalsIgnoreCase(action);
                case STOCK_MANAGEMENT:
                    return resource.toLowerCase().contains("stock") ||
                            resource.toLowerCase().contains("article") ||
                            resource.toLowerCase().contains("mvtstk");
                case ENTERPRISE_MANAGEMENT:
                    return resource.toLowerCase().contains("enterprise") ||
                            resource.toLowerCase().contains("users");
                default:
                    return false;
            }
        } catch (Exception e) {
            log.warn("Erreur lors de la vérification des permissions pour le token: {}", token, e);
            return false;
        }
    }

    @Override
    public TokenResponse renewToken(Long tokenId, Integer newExpirationDays, Users admin) {
        validateAdminPermissions(admin);

        AuthToken authToken = authTokenRepository.findById(tokenId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Token introuvable", ErrorCodes.TOKEN_NOT_FOUND));

        authToken.setExpiresAt(Instant.now().plus(newExpirationDays, ChronoUnit.DAYS));
        authToken.setIsActive(true);
        authToken = authTokenRepository.save(authToken);

        log.info("Token {} renouvelé pour {} jours par l'admin: {}",
                tokenId, newExpirationDays, admin.getEmail());

        return TokenResponse.fromEntity(authToken);
    }

    private void validateTokenRequest(TokenRequest request) {
        if (request.getUserEmail() == null || request.getUserEmail().trim().isEmpty()) {
            throw new InvalidEntityException("Email utilisateur requis", ErrorCodes.INVALID_ENTITY);
        }

        if (request.getTokenType() == null) {
            throw new InvalidEntityException("Type de token requis", ErrorCodes.INVALID_ENTITY);
        }

        if (request.getExpirationDays() == null || request.getExpirationDays() <= 0) {
            throw new InvalidEntityException("Durée d'expiration invalide", ErrorCodes.INVALID_ENTITY);
        }
    }

    private void validateAdminPermissions(Users admin) {
        if (admin == null) {
            throw new InvalidEntityException("Admin requis", ErrorCodes.INVALID_ENTITY);
        }

        boolean isAdmin = admin.getRoles().stream()
                .anyMatch(role -> Constants.ADMIN_ROLE.equals(role.getRoleName()));

        if (!isAdmin) {
            throw new InvalidOperationException("Seuls les administrateurs peuvent gérer les tokens",
                    ErrorCodes.INSUFFICIENT_PERMISSIONS);
        }
    }

    private Enterprise createNewEnterprise(TokenRequest request) {
        Enterprise enterprise = new Enterprise();
        enterprise.setNom(request.getEnterpriseName());
        enterprise.setEmail(request.getEnterpriseEmail());
        enterprise.setDescription(request.getEnterpriseDescription());
        enterprise.setNumTel(request.getEnterprisePhone());

        return enterpriseRepository.save(enterprise);
    }

    private Users createOrUpdateUser(TokenRequest request, Enterprise enterprise) {
        Optional<Users> existingUser = usersRepository.findByEmail(request.getUserEmail());

        if (existingUser.isPresent()) {
            Users user = existingUser.get();
            if (enterprise != null) {
                user.setEnterprise(enterprise);
                user = usersRepository.save(user);
            }
            return user;
        }

        // Créer un nouveau utilisateur
        Users newUser = new Users();
        newUser.setEmail(request.getUserEmail());
        newUser.setNom(request.getUserName());
        newUser.setPrenom(request.getUserPrenom());
        newUser.setEnterprise(enterprise);

        // Générer un mot de passe temporaire
        String tempPassword = generateTempPassword();
        newUser.setMoteDePasse(passwordEncoder.encode(tempPassword));

        newUser = usersRepository.save(newUser);

        // Assigner un rôle par défaut
        Roles userRole = new Roles();
        userRole.setRoleName(enterprise != null ? Constants.ENTERPRISE_USER_ROLE : Constants.CLIENT_ROLE);
        userRole.setUsers(newUser);
        rolesRepository.save(userRole);

        log.info("Nouvel utilisateur créé: {} avec mot de passe temporaire", newUser.getEmail());

        return newUser;
    }

    private String generateSecureToken() {
        StringBuilder token = new StringBuilder(TOKEN_PREFIX);

        // Ajouter un timestamp
        token.append(System.currentTimeMillis());

        // Ajouter des caractères aléatoires
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 32; i++) {
            token.append(chars.charAt(secureRandom.nextInt(chars.length())));
        }

        return token.toString();
    }

    private String generateTempPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            password.append(chars.charAt(secureRandom.nextInt(chars.length())));
        }

        return password.toString();
    }
}
