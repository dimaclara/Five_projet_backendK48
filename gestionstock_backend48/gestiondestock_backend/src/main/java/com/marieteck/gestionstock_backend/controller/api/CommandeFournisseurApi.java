package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.CommandeFournisseurDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(path = "/commandeFournisseur")
public interface CommandeFournisseurApi {

    @PostMapping(value =  "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    List<CommandeFournisseurDto> findAll();

    @GetMapping(value =  "/{idCommandeFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur") Long id);


    @GetMapping(value =  "/{codeCommandeFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @DeleteMapping(value =  "/delete/{idCommandeFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Delete an CommandeFournisseur by ID",
            description = "This method allows you to delete an CommandeFournisseur from the database using its ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the CommandeFournisseur to be deleted",
                            required = true,
                            in = ParameterIn.PATH
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "CommandeFournisseur successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "No CommandeFournisseur found with the given ID")
            }
    )
    void deleteById(@PathVariable("idCommandeFournisseur") Long id);
}
