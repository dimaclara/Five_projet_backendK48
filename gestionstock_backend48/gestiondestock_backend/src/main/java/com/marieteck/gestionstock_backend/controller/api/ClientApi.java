package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.ArticleDto;

import com.marieteck.gestionstock_backend.dto.ClientDto;
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


@RequestMapping(path = "/client")
public interface ClientApi {

    @PostMapping(value =  "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Save or update an client",
            description = "This method allows to save a new client or update an existing one by its ID."

    )

    ClientDto save(@RequestBody ClientDto clientDto);

    //---------------------------------------------------------------------------


    @GetMapping (value = "/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(
            summary = "Find client order by ID",
            description = "This method allows you to retrieve an client order by its ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the client order",
                            required = true,
                            in = ParameterIn.PATH
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "The client order was found"),
                    @ApiResponse(responseCode = "404", description = "No client order found with this ID")
            })
    ClientDto findById(@PathVariable("idClient") Long id);

    //---------------------------------------------------------------------------

    @GetMapping(value =  "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "This method allows to find the list of clients that exist in the database",
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
                            description = "No clients found in the database"
                    )
            }
    )
    List<ClientDto> findAll();

    //----------------------------------------------------------------------------

    @DeleteMapping(value =  "/delete/{idClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Delete an Client by ID",
            description = "This method allows you to delete an Client from the database using its ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the Client to be deleted",
                            required = true,
                            in = ParameterIn.PATH
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Client successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "No Client found with the given ID")
            }
    )
    void deleteById(@PathVariable("idClient") Long id);
}
