package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.CommandeClientDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(path = "/commandeClient")
public interface CommandeClientApi {

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @RouterOperation(operation =@Operation(summary = "Find Client order by Id ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "the Category object creates/ modifies"),
                    @ApiResponse(responseCode = "400", description = "the item Category is not valid")}) )

    CommandeClientDto save(@RequestBody CommandeClientDto commandeClientDto);

    //----------------------------------------------------------------------------------

    @GetMapping(value =  "/{idCommandeClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto findById(@PathVariable("idCommandeClient") Long id);

    //--------------------------------------------------------------------------------

    @GetMapping(value =  "/{codeCommandeClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto findByCode(@PathVariable("codeCommandeClient") String code);

    //-----------------------------------------------------------------------------------

    @GetMapping(value =  "/all",produces = MediaType.APPLICATION_JSON_VALUE)

    List<CommandeClientDto> findAll();

    //-------------------------------------------------------------------------------------------


    @DeleteMapping(value =  "/delete/{idCommandeClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Delete an CommandeClient by ID",
            description = "This method allows you to delete an CommandeClient from the database using its ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the CommandeClient to be deleted",
                            required = true,
                            in = ParameterIn.PATH
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "CommandeClient successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "No CommandeClient found with the given ID")
            }
    )
    void deleteById(@PathVariable("idCommandeClient") Long id);


}
