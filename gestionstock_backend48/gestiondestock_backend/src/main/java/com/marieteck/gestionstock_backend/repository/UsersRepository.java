package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(value = "select u from Users u where u.email = :email")
    Optional<Users> findByEmail(@Param("email") String email);

   // Users changePassword(String oldPassword, String newPassword);
}
