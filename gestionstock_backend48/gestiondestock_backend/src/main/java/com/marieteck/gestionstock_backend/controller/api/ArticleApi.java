package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.ArticleDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RequestMapping(path = "/gestiondestock/v1/article")
public interface ArticleApi {


    @PostMapping(value =  "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Save or update an article",
            description = "This method allows to save a new article or update an existing one by its ID."

    )


    ArticleDto save(@RequestBody ArticleDto articleDto);


//-------------------------------------------------------------------------------------------------

    @GetMapping(value =  "/{idArticle}",produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(
            summary = "Find article order by ID",
            description = "This method allows you to retrieve an article order by its ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the article order",
                            required = true,
                            in = ParameterIn.PATH
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "The article order was found"),
                    @ApiResponse(responseCode = "404", description = "No article order found with this ID")
            })

    ArticleDto findById(@PathVariable("idArticle") Long id);

    //----------------------------------------------------------------------------------------------
    @GetMapping(value =  "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "This method allows to find the list of articles that exist in the database",
            description = "Returns all the articles stored in the database.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of articles successfully retrieved",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ArticleDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No articles found in the database"
                    )
            }
    )

    List<ArticleDto> findAll();

    //-----------------------------------------------------------------------------------------------------

    @GetMapping(value = "/filter/{codeArticle}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Find the article by code",
            description = "This method allows you to search for an article using its unique code.",
            parameters = {
                    @Parameter(
                            name = "codeArticle",
                            description = "Code of the article to be retrieved",
                            required = true,
                            in = ParameterIn.PATH
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "The article was successfully found"),
                    @ApiResponse(responseCode = "404", description = "No article found with the given code")
            })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    //-----------------------------------------------------------------------------------------------

    @DeleteMapping(value = "/{idArticle}")
    @Operation(
            summary = "Delete an article by ID",
            description = "This method allows you to delete an article from the database using its ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the article to be deleted",
                            required = true,
                            in = ParameterIn.PATH
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Article successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "No article found with the given ID")
            }
    )
    void deleteById(@PathVariable("idArticle") Long id);
}
