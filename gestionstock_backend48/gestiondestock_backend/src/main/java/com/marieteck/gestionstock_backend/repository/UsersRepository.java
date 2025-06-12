package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
