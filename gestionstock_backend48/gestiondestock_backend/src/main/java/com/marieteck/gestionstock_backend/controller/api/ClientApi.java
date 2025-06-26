package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.ClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;

public interface ClientApi {

    @PostMapping(value = APP_ROOT + "/client/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @RouterOperation(operation =@Operation(summary = "this method allows you to creates a objet Articles ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "the Category object creates/ modifies"),
                    @ApiResponse(responseCode = "400", description = "the item Category is not valid")}) )
    ClientDto save(@RequestBody ClientDto clientDto);

    //---------------------------------------------------------------------------


    @GetMapping (value = APP_ROOT + "/client/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)

    @RouterOperation(operation =@Operation(summary = "this method allows you find Client by Id  ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Client succefull found in DB"),
                    @ApiResponse(responseCode = "404", description = "No client exist in the DB with the provided ID")}))
    ClientDto findById(@PathVariable("idClient") Long id);

    //---------------------------------------------------------------------------

    @GetMapping(value = APP_ROOT + "/client/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @RouterOperation(operation =@Operation(summary = "this method allows you find all the Client  ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "the list of Client"),
                    @ApiResponse(responseCode = "404",description = "an empty list")

            }) )
    List<ClientDto> findAll();

    //----------------------------------------------------------------------------

    @DeleteMapping(value = APP_ROOT + "/client/delete/{idClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    @RouterOperation(operation =@Operation(summary = "this method allows you to delete Client by Id ",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Client succefull delete"),

            }) )
    void deleteById(@PathVariable("idClient") Long id);
}
