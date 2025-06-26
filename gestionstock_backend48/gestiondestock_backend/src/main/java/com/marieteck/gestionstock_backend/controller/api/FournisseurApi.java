package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.FournisseurDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;
@RequestMapping(path = "/fournisseur")

public interface FournisseurApi {

    @PostMapping(value =  "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(@RequestBody FournisseurDto fournisseurDto);


    @GetMapping (value =  "/{idFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findById(@PathVariable("idFournisseur") Long id);

    @GetMapping (value =  "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @DeleteMapping(value =  "/delete/{idFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
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
    void deleteById(@PathVariable("idFournisseur") Long id);
}
