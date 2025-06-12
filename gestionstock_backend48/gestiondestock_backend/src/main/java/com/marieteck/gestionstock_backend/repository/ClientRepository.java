package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
