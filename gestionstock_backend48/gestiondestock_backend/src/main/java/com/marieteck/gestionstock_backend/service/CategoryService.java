package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto findById(Long id);

    CategoryDto findByCode(String code);

    List<CategoryDto> findAll();

    void deleteById(Long id);

}
