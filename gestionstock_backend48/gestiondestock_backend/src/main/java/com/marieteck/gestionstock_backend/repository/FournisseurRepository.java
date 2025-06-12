package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
}
