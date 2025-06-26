package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;

public interface CategoryApi {

     @PostMapping(value = APP_ROOT + "/category/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
     @RouterOperation(operation =@Operation(summary = "Find article order by Id ",
             responses ={
                     @ApiResponse(responseCode = "200", description = "Category found"),
                     @ApiResponse(responseCode = "400", description = "the item Category is not valid")}) )
     CategoryDto save(@RequestBody CategoryDto category);

      //----------------------------------------------------------------------------------------------

    @GetMapping(value = APP_ROOT + "/category/{idCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    @RouterOperation(operation =@Operation(summary = "this method allows you find Category by Id  ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Category succefull found in DB"),
                    @ApiResponse(responseCode = "404", description = "No Category exist in the DB with the provided ID")}) )
    CategoryDto findById(@PathVariable("idCategory") Long id);

    //----------------------------------------------------------------------------------------------

    @GetMapping(value = APP_ROOT + "/category/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @RouterOperation(operation =@Operation(summary = "this method allows you find Category by Code  ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Category succefull found"),
                    @ApiResponse(responseCode = "404", description = " No Category exist in the DB with the provided Code")}) )
    CategoryDto findByCode(@PathVariable("codeCategory") String code);


    //----------------------------------------------------------------------------------------------

    @GetMapping(value =APP_ROOT + "/category/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @RouterOperation(operation =@Operation(summary = "this method allows you find all the Category  ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "the list of article"),
                    @ApiResponse(responseCode = "404",description = "an empty list")

            }) )
    List<CategoryDto> findAll();

    //----------------------------------------------------------------------------------------------
    @GetMapping(value = APP_ROOT +"/category/delete/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)

    @RouterOperation(operation =@Operation(summary = "this method allows you to delete Category by Id ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Category succefull delete"),

            }) )
    void deleteById(@PathVariable("idCategory") Long id);


}
