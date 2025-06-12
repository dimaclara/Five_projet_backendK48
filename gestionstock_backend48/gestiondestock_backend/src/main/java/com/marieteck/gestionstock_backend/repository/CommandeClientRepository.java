package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.dto.LigneCommandeClientDto;
import com.marieteck.gestionstock_backend.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Long> {

}
