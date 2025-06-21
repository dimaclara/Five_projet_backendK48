package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;

public interface CategoryApi {

     @PostMapping(value = APP_ROOT + "/category/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto category);


    CategoryDto findById(@PathVariable("idCategory") Long id);

    @GetMapping(value = APP_ROOT + "/category/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findByCode(@PathVariable("codeCategory") String code);

    @GetMapping(value =APP_ROOT + "/category/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();

    @GetMapping(value = APP_ROOT +"/category/delete/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteById(@PathVariable("idCategory") Long id);


}
