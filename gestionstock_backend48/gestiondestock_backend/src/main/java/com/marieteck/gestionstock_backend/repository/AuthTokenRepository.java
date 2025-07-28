package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.AuthToken;
import com.marieteck.gestionstock_backend.model.Enterprise;
import com.marieteck.gestionstock_backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {

    Optional<AuthToken> findByTokenAndIsActiveTrue(String token);

    List<AuthToken> findByUserAndIsActiveTrue(Users user);

    List<AuthToken> findByEnterpriseAndIsActiveTrue(Enterprise enterprise);

    @Query("SELECT t FROM AuthToken t WHERE t.isActive = true AND t.expiresAt > :now")
    List<AuthToken> findActiveTokens(@Param("now") Instant now);

    @Query("SELECT t FROM AuthToken t WHERE t.isActive = true AND t.expiresAt <= :now")
    List<AuthToken> findExpiredTokens(@Param("now") Instant now);

    @Query("SELECT t FROM AuthToken t WHERE t.createdBy = :admin ORDER BY t.creationDate DESC")
    List<AuthToken> findTokensCreatedByAdmin(@Param("admin") Users admin);

    @Query("SELECT COUNT(t) FROM AuthToken t WHERE t.enterprise = :enterprise AND t.isActive = true")
    Long countActiveTokensByEnterprise(@Param("enterprise") Enterprise enterprise);

    @Query("SELECT t FROM AuthToken t WHERE t.user.email = :email AND t.isActive = true")
    List<AuthToken> findActiveTokensByUserEmail(@Param("email") String email);

    void deleteByUserAndIsActiveTrue(Users user);
}
