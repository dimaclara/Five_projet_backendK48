package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.VentesDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;

public interface VentesApi {

    @PostMapping(value = APP_ROOT + "/ventes/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto save(@RequestBody VentesDto ventesDto);

    @GetMapping(value = APP_ROOT + "/vente/{idVente}/", produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto findById(@PathVariable("idVente") Long id);

    @GetMapping( value = APP_ROOT +"/vente/{codeVente}/", produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto findByCode(@PathVariable("codeVente") String code);

    @GetMapping(value = APP_ROOT + "/vente/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<VentesDto> findAll();

    @DeleteMapping(APP_ROOT + "/vente/delete/{idVente}/")
    void deleteById(@PathVariable("idVente") Long id);
}
