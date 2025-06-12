package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.CategoryDto;
import com.marieteck.gestionstock_backend.exception.EntityNotFoundException;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.model.Category;
import com.marieteck.gestionstock_backend.repository.CategoryRepository;
import com.marieteck.gestionstock_backend.service.CategoryService;
import com.marieteck.gestionstock_backend.validator.CategoryValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;


    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        List<String> errors = CategoryValidator.validate(categoryDto);
        if (!errors.isEmpty()) {
            log.error("Category is not valide  {}", categoryDto);
            throw new InvalidEntityException("category is not available", ErrorCodes.CATEGORY_ALREADY_IN_USE,errors);

        }
        Category categorySaved = categoryRepository.save(CategoryDto.toEntity(categoryDto));
        return  CategoryDto.fromEntity(categorySaved);
    }


    @Override
    public CategoryDto findById(Long id) {
        if (id == null){
            log.error("id is null");
            return null;
        }
        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("not Category with this id :" + id,
                        ErrorCodes.CATEGORY_NOT_FOUND)
                );


    }


    @Override
    public CategoryDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("code is null");
            return null;
        }
        return categoryRepository.findByCode(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("not Category has this code" + code + "not found in DB" +
                        ErrorCodes.CATEGORY_NOT_FOUND));
    }


    public List<CategoryDto> findAll() {

        return categoryRepository.findAll()
                .stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("id is null");
            return;
        }
        categoryRepository.deleteById(id);
    }
}
