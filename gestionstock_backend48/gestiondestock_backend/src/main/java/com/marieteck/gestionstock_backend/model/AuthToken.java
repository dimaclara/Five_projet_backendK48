package com.marieteck.gestionstock_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "auth_tokens")

public class AuthToken extends AbstractEntity{

    @Column(name = "token", unique = true, nullable = false, length = 500)
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "last_used")
    private Instant lastUsed;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users createdBy; // Admin qui a créé le token

    @Column(name = "purpose", length = 1000)
    private String purpose; // Description du but du token

    @Column(name = "ip_restrictions", length = 500)
    private String ipRestrictions; // IPs autorisées (optionnel)

    public enum TokenType {
        ENTERPRISE_MANAGEMENT, // Token pour gérer une entreprise
        STOCK_MANAGEMENT,      // Token pour gérer les stocks
        READ_ONLY,            // Token en lecture seule
        FULL_ACCESS           // Accès complet
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    public boolean isValid() {
        return isActive && !isExpired();
    }
}
