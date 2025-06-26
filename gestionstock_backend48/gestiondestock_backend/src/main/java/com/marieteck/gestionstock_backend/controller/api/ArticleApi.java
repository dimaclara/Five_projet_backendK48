package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.ArticleDto;
import com.marieteck.gestionstock_backend.dto.LigneCommandeClientDto;
import com.marieteck.gestionstock_backend.dto.LigneCommandeFournisseurDto;
import com.marieteck.gestionstock_backend.dto.LigneVenteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Value;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;



public interface ArticleApi {


    @PostMapping(value =APP_ROOT + "/article/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    @RouterOperation(operation =@Operation(summary = "this method allows you to save or edit an article ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Category succefull create or modified"),
                    @ApiResponse(responseCode = "400", description = "Invalid Article supplied "),
                    }) )

    ArticleDto save(@RequestBody ArticleDto articleDto);


//-------------------------------------------------------------------------------------------------

    @GetMapping(value = APP_ROOT + "/article/{idArticle}",produces = MediaType.APPLICATION_JSON_VALUE)

    @RouterOperation(operation =@Operation(summary = "Find article order by Id ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Article found"),
                    @ApiResponse(responseCode = "404", description = "Article not found")}) )

    ArticleDto findById(@PathVariable("idArticle") Long id);

    //----------------------------------------------------------------------------------------------
    @GetMapping(value = APP_ROOT + "/article/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @RouterOperation(operation =@Operation(summary = "this method allow to find the list of  Article that exist in the DB ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "the List of articles/An Empty list")
            }))
     List<ArticleDto> findAll();

    //-----------------------------------------------------------------------------------------------------

    @GetMapping(value = APP_ROOT + "/article/{codeArticle}",produces = MediaType.APPLICATION_JSON_VALUE)
    @RouterOperation(operation =@Operation(summary = "found the Article By code Articles ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Articles succeful found"),
                    @ApiResponse(responseCode = "404", description = " code Articles not valid")}) )
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    //-----------------------------------------------------------------------------------------------

    @DeleteMapping(value = APP_ROOT  + "/article/{idArticle}")
    @RouterOperation(operation =@Operation(summary = "delete a Article by Id",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Articles succeful delete"),
                    @ApiResponse(responseCode = "404", description = "this id Article was not found in DB")}) )
    void deleteById(@PathVariable("idArticle") Long id);
}
