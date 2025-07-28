package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.PexelApi;
import com.marieteck.gestionstock_backend.dto.PexelsPhotoDto;
import com.marieteck.gestionstock_backend.dto.PexelsSearchResponseDto;
import com.marieteck.gestionstock_backend.model.ImageSize;
import com.marieteck.gestionstock_backend.service.PexelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
public class PexelController implements PexelApi {


    private final PexelService pexelService;
    @Autowired
    public PexelController(PexelService pexelService) {
        this.pexelService = pexelService;
    }

    @Override
    public String savePhoto(String keyword) {
        log.info("found the image with keyword {}", keyword);
        return pexelService.savePhoto(keyword);
    }

    @Override
    public ResponseEntity<PexelsSearchResponseDto> searchPhotos(String keyword, Integer page, Integer perPage) {
        log.info("Recherche de photos pour: {}, page: {}, perPage: {}", keyword, page, perPage);

        Optional<PexelsSearchResponseDto> result = pexelService.searchPhotos(keyword, page, perPage);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<PexelsPhotoDto> getPhotoById(Long id) {
        log.info("Récupération de la photo avec l'ID: {}", id);

        Optional<PexelsPhotoDto> photo = pexelService.getPhotoById(id);

        if (photo.isPresent()) {
            return ResponseEntity.ok(photo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> getPhotoUrl(String keyword, ImageSize imageSize) {
        log.info("Récupération de l'URL d'image pour: {}, taille: {}", keyword, imageSize);

        String photoUrl = pexelService.getPhotoUrl(keyword, imageSize);

        if (photoUrl != null) {
            return ResponseEntity.ok(photoUrl);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<String> findImageForArticle(String articleDesignation) {
        log.info("Recherche d'image automatique pour l'article: {}", articleDesignation);

        String imageUrl = pexelService.findBestImageForArticle(articleDesignation);

        if (imageUrl != null) {
            return ResponseEntity.ok(imageUrl);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<PexelsPhotoDto> getPhotoWithAllSizes(String keyword) {
        log.info("Récupération de la photo avec toutes les tailles pour: {}", keyword);

        Optional<PexelsPhotoDto> photo = pexelService.getPhotoWithAllSizes(keyword);

        if (photo.isPresent()) {
            return ResponseEntity.ok(photo.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> testExternalApiConnection() {
        log.info("Test de connexion à l'API externe Pexels");

        Map<String, Object> testResult = new HashMap<>();
        testResult.put("timestamp", LocalDateTime.now());
        testResult.put("apiUrl", "https://api.pexels.com/v1/");

        try {
            // Test simple de recherche
            Optional<PexelsSearchResponseDto> searchResult = pexelService.searchPhotos("computer", 1, 1);

            if (searchResult.isPresent()) {
                testResult.put("status", "SUCCESS");
                testResult.put("message", "Connexion à l'API externe Pexels réussie");
                testResult.put("totalResults", searchResult.get().getTotalResults());
                testResult.put("photosReturned", searchResult.get().getPhotos().size());

                if (!searchResult.get().getPhotos().isEmpty()) {
                    PexelsPhotoDto firstPhoto = searchResult.get().getPhotos().get(0);
                    testResult.put("sampleImageId", firstPhoto.getId());
                    testResult.put("sampleImageUrl", firstPhoto.getSrc() != null ? firstPhoto.getSrc().getMedium() : null);
                    testResult.put("photographer", firstPhoto.getPhotographer());
                }

                log.info("✅ Test connexion API externe Pexels réussi");
                return ResponseEntity.ok(testResult);

            } else {
                testResult.put("status", "FAILURE");
                testResult.put("message", "Aucune réponse de l'API externe Pexels");
                log.error("❌ Test connexion API externe Pexels échoué - Aucune réponse");
                return ResponseEntity.status(503).body(testResult);
            }

        } catch (Exception e) {
            testResult.put("status", "ERROR");
            testResult.put("message", "Erreur lors de la connexion à l'API externe Pexels: " + e.getMessage());
            testResult.put("error", e.getClass().getSimpleName());
            log.error("❌ Test connexion API externe Pexels échoué", e);
            return ResponseEntity.status(500).body(testResult);
        }
    }
}
