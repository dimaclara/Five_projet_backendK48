package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
