package com.marieteck.gestionstock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marieteck.gestionstock_backend.model.Article;
import com.marieteck.gestionstock_backend.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto {

    private Long id;

    private String code;

    private String designation;

    private Integer idEnterprise;

    @JsonIgnore
    private List<ArticleDto> articles;

    //creer une methode qui va me renvoyer categoryDto

    public static CategoryDto fromEntity(Category category) {
        if (category == null) {
            return null;

            //TODO throw on exception
        }
        // category <--- categoryDto
        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .idEnterprise(category.getIdEntreprise())
                .build();


    }
    public static Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());
        category.setIdEntreprise(categoryDto.getIdEnterprise());
        return category;
    }
}
