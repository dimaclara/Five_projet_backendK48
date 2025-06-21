package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.CommandeFournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;

public interface CommandeFournisseurApi {

    @PostMapping(value = APP_ROOT + "/commandeFournisseur/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    List<CommandeFournisseurDto> findAll();

    @GetMapping(value = APP_ROOT + "/commandeFournisseur/{idCommandeFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur") Long id);


    @GetMapping(value = APP_ROOT + "/commandeFournisseur/{codeCommandeFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @DeleteMapping(value = APP_ROOT + "/commandeFournisseur/delete/{idCommandeFournisseur}",produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteById(@PathVariable("idCommandeFournisseur") Long id);
}
