package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.CategoryDto;

import java.util.List;

public interface CategoryApi {

    CategoryDto save(CategoryDto category);

    CategoryDto findById(Long id);

    CategoryDto findByCode(String code);

    List<CategoryDto> findAll();

    void deleteById(Long id);


}
