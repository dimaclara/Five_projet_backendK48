package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.CategoryApi;
import com.marieteck.gestionstock_backend.dto.CategoryDto;
import com.marieteck.gestionstock_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController

public class CategoryController implements CategoryApi {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryService.findById(id);
    }

    @Override
    public CategoryDto findByCode(String code) {
        return categoryService.findByCode(code);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        categoryService.deleteById(id);

    }
}
