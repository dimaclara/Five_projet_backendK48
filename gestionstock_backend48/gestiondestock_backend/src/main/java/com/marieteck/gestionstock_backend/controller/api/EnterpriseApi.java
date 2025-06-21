package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.EnterpriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;

public interface EnterpriseApi {

    @PostMapping(value = APP_ROOT + "/enterprise/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    EnterpriseDto save(@RequestBody EnterpriseDto dto);

    @GetMapping (value = APP_ROOT + "/enterprise/{idEnterprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    EnterpriseDto findById(@PathVariable("idEnterprise") Long id);

    @GetMapping (value = APP_ROOT + "/enterprise/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EnterpriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/enterprise/delete/{idEnterprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteById(@PathVariable("idEnterprise") Long id);
}
