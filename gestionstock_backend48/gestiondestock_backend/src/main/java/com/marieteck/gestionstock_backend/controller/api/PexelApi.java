package com.marieteck.gestionstock_backend.controller.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;

public interface PexelApi {

    @PostMapping(value = APP_ROOT + "/pexel/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String savePhoto(@RequestBody String keyword);
    
}
