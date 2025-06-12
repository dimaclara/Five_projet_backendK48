package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MvtStkRepository extends JpaRepository<MvtStk, Long> {
}
