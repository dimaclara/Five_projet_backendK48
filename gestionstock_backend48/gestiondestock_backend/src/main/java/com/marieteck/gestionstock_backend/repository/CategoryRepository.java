package com.marieteck.gestionstock_backend.repository;

import com.marieteck.gestionstock_backend.dto.CategoryDto;
import com.marieteck.gestionstock_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCode(String code);
}
