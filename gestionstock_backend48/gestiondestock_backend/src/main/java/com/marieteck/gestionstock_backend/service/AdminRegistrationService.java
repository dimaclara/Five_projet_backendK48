package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.auth.AdminRegistrationRequest;
import com.marieteck.gestionstock_backend.dto.auth.AdminRegistrationResponse;
import com.marieteck.gestionstock_backend.dto.auth.TokenLoginRequest;
import com.marieteck.gestionstock_backend.dto.auth.TokenLoginResponse;

public interface AdminRegistrationService {
    AdminRegistrationResponse registerAdmin(AdminRegistrationRequest request);
    TokenLoginResponse authenticateWithToken(TokenLoginRequest request);
    boolean isValidEmail(String email);
    boolean isEmailAlreadyUsed(String email);
}
