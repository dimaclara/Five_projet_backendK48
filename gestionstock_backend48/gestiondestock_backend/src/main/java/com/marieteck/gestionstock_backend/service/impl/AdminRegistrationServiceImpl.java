package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.auth.*;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.model.AuthToken;
import com.marieteck.gestionstock_backend.model.Enterprise;
import com.marieteck.gestionstock_backend.model.Roles;
import com.marieteck.gestionstock_backend.model.Users;
import com.marieteck.gestionstock_backend.repository.AuthTokenRepository;
import com.marieteck.gestionstock_backend.repository.EnterpriseRepository;
import com.marieteck.gestionstock_backend.repository.RolesRepository;
import com.marieteck.gestionstock_backend.repository.UsersRepository;
import com.marieteck.gestionstock_backend.service.AdminRegistrationService;
import com.marieteck.gestionstock_backend.service.AuthTokenService;
import com.marieteck.gestionstock_backend.utils.Constants;
import com.marieteck.gestionstock_backend.utils.JwtUtils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class AdminRegistrationServiceImpl implements AdminRegistrationService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;



    @Override
    public AdminRegistrationResponse registerAdmin(AdminRegistrationRequest request) {
        log.info("Début de l'enregistrement admin pour: {}", request.getEmail());

        // Validation des données
        validateRegistrationRequest(request);

        // Vérifier si l'email existe déjà
        if (isEmailAlreadyUsed(request.getEmail())) {
            throw new InvalidEntityException("Un utilisateur avec cet email existe déjà", ErrorCodes.USERS_ALREADY_EXISTS);
        }

        // Créer l'entreprise si spécifiée
        Enterprise enterprise = null;
        if (request.getEnterpriseName() != null && !request.getEnterpriseName().trim().isEmpty()) {
            enterprise = createEnterprise(request);
        }

        // Créer l'utilisateur admin
        Users admin = createAdminUser(request, enterprise);

        // Assigner le rôle ADMIN
        assignAdminRole(admin);

        // Générer JWT token pour connexion immédiate
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        admin.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        ExtendedUser extendedUser = new ExtendedUser(
                admin.getEmail(),
                admin.getMotDePasse(),
                admin.getEnterprise() != null ? admin.getEnterprise().getId() : null,
                authorities
        );

        String jwtToken = jwtUtils.generateToken(extendedUser);

        AdminRegistrationResponse response = new AdminRegistrationResponse();
        response.setAdminId(admin.getId());
        response.setEmail(admin.getEmail());
        response.setNom(admin.getNom());
        response.setPrenom(admin.getPrenom());
        response.setMessage("Admin enregistré avec succès");
        response.setAccessToken(jwtToken);
        response.setEnterpriseId(enterprise != null ? enterprise.getId() : null);
        response.setEnterpriseName(enterprise != null ? enterprise.getNom() : null);

        log.info("Admin enregistré avec succès: {}", admin.getEmail());
        return response;
    }

    @Override
    public TokenLoginResponse authenticateWithToken(TokenLoginRequest request) {
        log.info("Tentative d'authentification avec token");

        if (request.getToken() == null || request.getToken().trim().isEmpty()) {
            throw new InvalidEntityException("Token requis", ErrorCodes.INVALID_ENTITY);
        }

        // Valider le token
        AuthToken authToken = authTokenService.validateToken(request.getToken());

        // Mettre à jour la dernière utilisation
        authTokenService.updateLastUsed(request.getToken());

        Users user = authToken.getUser();

        // Créer un ExtendedUser pour la génération JWT
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        ExtendedUser extendedUser = new ExtendedUser(
                user.getEmail(),
                user.getMotDePasse(),
                user.getEnterprise() != null ? user.getEnterprise().getId() : null,
                authorities
        );

        String jwtToken = jwtUtils.generateToken(extendedUser);

        TokenLoginResponse response = new TokenLoginResponse();
        response.setAccessToken(jwtToken);
        response.setUserId(user.getId());
        response.setUserEmail(user.getEmail());
        response.setUserName(user.getNom() + " " + user.getPrenom());
        response.setEnterpriseName(user.getEnterprise() != null ? user.getEnterprise().getNom() : null);
        response.setTokenType(authToken.getTokenType());
        response.setTokenExpiresAt(authToken.getExpiresAt());
        response.setMessage("Authentification réussie");

        log.info("Authentification par token réussie pour: {}", user.getEmail());
        return response;
    }

    @Override
    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        // Validation simple mais efficace
        return email.contains("@") &&
                email.indexOf("@") > 0 &&
                email.indexOf("@") < email.length() - 1 &&
                email.indexOf("@") == email.lastIndexOf("@") &&
                email.contains(".") &&
                email.indexOf(".") > email.indexOf("@") &&
                email.length() >= 5;
    }

    @Override
    public boolean isEmailAlreadyUsed(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }

    private void validateRegistrationRequest(AdminRegistrationRequest request) {
        if (request.getNom() == null || request.getNom().trim().isEmpty()) {
            throw new InvalidEntityException("Nom requis", ErrorCodes.INVALID_ENTITY);
        }

        if (request.getPrenom() == null || request.getPrenom().trim().isEmpty()) {
            throw new InvalidEntityException("Prénom requis", ErrorCodes.INVALID_ENTITY);
        }

        if (!isValidEmail(request.getEmail())) {
            throw new InvalidEntityException("Email invalide", ErrorCodes.INVALID_ENTITY);
        }

        if (request.getMotDePasse() == null || request.getMotDePasse().length() < 6) {
            throw new InvalidEntityException("Mot de passe doit contenir au moins 6 caractères", ErrorCodes.INVALID_ENTITY);
        }

        if (!request.getMotDePasse().equals(request.getConfirmMotDePasse())) {
            throw new InvalidEntityException("Les mots de passe ne correspondent pas", ErrorCodes.INVALID_ENTITY);
        }
    }

    private Enterprise createEnterprise(AdminRegistrationRequest request) {
        Enterprise enterprise = new Enterprise();
        enterprise.setNom(request.getEnterpriseName());
        enterprise.setEmail(request.getEnterpriseEmail());
        enterprise.setDescription(request.getEnterpriseDescription());
        enterprise.setNumTel(request.getEnterprisePhone());

        enterprise = enterpriseRepository.save(enterprise);
        log.info("Entreprise créée: {}", enterprise.getNom());
        return enterprise;
    }

    private Users createAdminUser(AdminRegistrationRequest request, Enterprise enterprise) {
        Users admin = new Users();
        admin.setNom(request.getNom());
        admin.setPrenom(request.getPrenom());
        admin.setEmail(request.getEmail());
        admin.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        admin.setEnterprise(enterprise);

        admin = usersRepository.save(admin);
        log.info("Utilisateur admin créé: {}", admin.getEmail());
        return admin;
    }

    private void assignAdminRole(Users admin) {
        Roles adminRole = new Roles();
        adminRole.setRoleName(Constants.ADMIN_ROLE);
        adminRole.setUsers(admin);
        rolesRepository.save(adminRole);
        log.info("Rôle ADMIN assigné à: {}", admin.getEmail());
    }



}
