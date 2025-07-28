package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.PexelsPhotoDto;
import com.marieteck.gestionstock_backend.dto.PexelsSearchResponseDto;
import com.marieteck.gestionstock_backend.model.ImageSize;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;

public interface PexelApi {

    @PostMapping(value = APP_ROOT + "/pexel/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String savePhoto(@RequestBody String keyword);


    @GetMapping(value = APP_ROOT + "/pexel/search")
    ResponseEntity<PexelsSearchResponseDto> searchPhotos(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer perPage
    );

    @GetMapping(value =  "/pexel/photo/{id}")
    ResponseEntity<PexelsPhotoDto> getPhotoById(@PathVariable Long id);

    @GetMapping(value =  "/pexel/photo-url")
    ResponseEntity<String> getPhotoUrl(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "MEDIUM") ImageSize imageSize
    );

    @GetMapping(value =  "/pexel/article-image")
    ResponseEntity<String> findImageForArticle(@RequestParam String articleDesignation);

    @GetMapping(value =  "/pexel/photo-all-sizes")
    ResponseEntity<PexelsPhotoDto> getPhotoWithAllSizes(@RequestParam String keyword);

    /**
     * Endpoint de diagnostic pour tester la connexion à l'API externe Pexels
     */
    @GetMapping(value = APP_ROOT + "/pexel/test-connection")
    ResponseEntity<Map<String, Object>> testExternalApiConnection();
    
}
