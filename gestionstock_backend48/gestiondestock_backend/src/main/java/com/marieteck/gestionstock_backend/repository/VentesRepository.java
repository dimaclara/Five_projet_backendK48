package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.Article;
import com.marieteck.gestionstock_backend.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VentesRepository extends JpaRepository<Ventes, Long> {

    Optional<Ventes> findByCode(String code);

}
