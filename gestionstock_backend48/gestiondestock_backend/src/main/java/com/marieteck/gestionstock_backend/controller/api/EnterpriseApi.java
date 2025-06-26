package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.EnterpriseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RequestMapping(path = "/enterprise")
public interface EnterpriseApi {

    @PostMapping(value =  "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    EnterpriseDto save(@RequestBody EnterpriseDto enterpriseDto);

    @GetMapping (value = "/{idEnterprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    EnterpriseDto findById(@PathVariable("idEnterprise") Long id);

    @GetMapping (value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EnterpriseDto> findAll();

    @DeleteMapping(value =  "/delete/{idEnterprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Delete an Enterprise by ID",
            description = "This method allows you to delete an Enterprise from the database using its ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the Enterprise to be deleted",
                            required = true,
                            in = ParameterIn.PATH
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Enterprise successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "No Enterprise found with the given ID")
            }
    )
    void deleteById(@PathVariable("idEnterprise") Long id);
}
