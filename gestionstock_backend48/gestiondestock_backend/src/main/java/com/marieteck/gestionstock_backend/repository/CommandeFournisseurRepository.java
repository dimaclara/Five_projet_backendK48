package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Long> {
  Optional<CommandeFournisseur> findByCode(String code);
}
