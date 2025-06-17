package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

   // Users changePassword(String oldPassword, String newPassword);
}
