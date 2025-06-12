package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.Article;
import com.marieteck.gestionstock_backend.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentesRepository extends JpaRepository<Ventes, Long> {

}
