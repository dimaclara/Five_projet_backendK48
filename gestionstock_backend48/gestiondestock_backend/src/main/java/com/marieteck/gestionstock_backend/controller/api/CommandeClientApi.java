package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.CommandeClientDto;
import com.marieteck.gestionstock_backend.dto.LigneCommandeClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;

public interface CommandeClientApi {

    @PostMapping(value = APP_ROOT + "/commandeClient/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto save(@RequestBody CommandeClientDto commandeClientDto);

    @GetMapping(value = APP_ROOT + "/commandeClient/{idCommandeClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto findById(@PathVariable("idCommandeClient") Long id);

    @GetMapping(value = APP_ROOT + "/commandeClient/{codeCommandeClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto findByCode(@PathVariable("codeCommandeClient") String code);


    @GetMapping(value = APP_ROOT + "/commandeClient/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeClientDto> findAll();


    @DeleteMapping(value = APP_ROOT + "/commandeClient/delete/{idCommandeClient}",produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteById(@PathVariable("idCommandeClient") Long id);


}
