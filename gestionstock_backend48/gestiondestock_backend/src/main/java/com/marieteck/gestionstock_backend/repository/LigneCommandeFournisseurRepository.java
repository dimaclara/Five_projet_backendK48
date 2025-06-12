package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Long> {

    List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(Long idCommande);
}
