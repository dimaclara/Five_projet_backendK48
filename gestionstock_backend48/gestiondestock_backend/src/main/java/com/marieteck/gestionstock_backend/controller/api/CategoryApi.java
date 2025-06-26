package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.CategoryDto;
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


@RequestMapping(path = "/category")
public interface CategoryApi {

     @PostMapping(value =  "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
     @Operation(
             summary = "Save or update an Category",
             description = "This method allows to save a new Category or update an existing one by its ID."

     )
     CategoryDto save(@RequestBody CategoryDto category);

      //----------------------------------------------------------------------------------------------

    @GetMapping(value =  "/{idCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Find category by ID",
            description = "This method allows you to retrieve a category from the database by its ID.",

            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Category successfully found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No category found with the given ID"
                    )
            }
    )
    CategoryDto findById(@PathVariable("idCategory") Long id);

    //----------------------------------------------------------------------------------------------

    @GetMapping(value = "/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "This method allows you to find a category by code",
            description = "Retrieves a category from the database using its unique code.",
            parameters = {
                    @Parameter(
                            name = "codeCategory",
                            description = "Code of the category to retrieve",
                            required = true,
                            in = ParameterIn.PATH
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Category successfully found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No category found with the given code"
                    )
            }
    )
    CategoryDto findByCode(@PathVariable("codeCategory") String code);


    //----------------------------------------------------------------------------------------------

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "This method allows to find the list of categories that exist in the database",
            description = "Returns all the categories stored in the database.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of categories successfully retrieved",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No categories found in the database"
                    )
            }
    )
    List<CategoryDto> findAll();

    //----------------------------------------------------------------------------------------------
    @GetMapping(value = "/delete/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(
            summary = "Delete an Category by ID",
            description = "This method allows you to delete an Category from the database using its ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the Category to be deleted",
                            required = true,
                            in = ParameterIn.PATH
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Category successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "No Category found with the given ID")
            }
    )
    void deleteById(@PathVariable("idCategory") Long id);


}
