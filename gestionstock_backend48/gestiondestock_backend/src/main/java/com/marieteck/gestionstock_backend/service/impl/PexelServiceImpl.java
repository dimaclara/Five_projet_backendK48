package com.marieteck.gestionstock_backend.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marieteck.gestionstock_backend.dto.PexelsPhotoDto;
import com.marieteck.gestionstock_backend.dto.PexelsSearchResponseDto;
import com.marieteck.gestionstock_backend.model.ImageSize;
import com.marieteck.gestionstock_backend.service.PexelService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.marieteck.gestionstock_backend.model.ImageSize.*;




@Service

@Slf4j
public class PexelServiceImpl implements PexelService {



    private  final WebClient webClient;
    private final ObjectMapper objectMapper;

    public PexelServiceImpl(@Qualifier("pexelsWebClient") WebClient webClient) {
        this.webClient = webClient;
        this.objectMapper = new ObjectMapper();
    }

    @Override

    public String savePhoto(String keyword) {


        return getPhotoUrl(keyword, ImageSize.MEDIUM);
//        String response = webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("search")
//                        .queryParam("query",keyword)
//                        .queryParam("per_page",1)
//                        .build())
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//
//        if (response == null){
//            return null;
//        }
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.readTree(response);
//            JsonNode photos = jsonNode.get("photos");
//            if (!photos.isArray() || photos.isEmpty()) {
//                return null;
//            }
//            JsonNode firstPhoto = photos.get(0);
//            return firstPhoto.path("src").path("medium").asText(null);
//
//
//        }catch (Exception e){
//            log.error("Erreur lors du parsing de la réponse Pexels", e);
//            return null;


        }
//        @Override
        public String getPhotoUrl(String keyword, ImageSize imageSize) {
            try {
                Optional<PexelsPhotoDto> photo = getPhotoWithAllSizes(keyword);
                if (photo.isPresent()) {
                    return extractUrlBySize(photo.get(), imageSize);
                }
                return null;
            } catch (Exception e) {
                log.error("Erreur lors de la récupération de l'image pour le mot-clé: {}", keyword, e);
                return null;
            }
        }

        @Override
        public Optional<PexelsSearchResponseDto> searchPhotos(String keyword, Integer page, Integer perPage) {
            try {
                // Validation des paramètres
                int validPage = (page != null && page > 0) ? page : 1;
                int validPerPage = (perPage != null && perPage > 0 && perPage <= 80) ? perPage : 10;

                String response = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("search")
                                .queryParam("query", keyword)
                                .queryParam("page", validPage)
                                .queryParam("per_page", validPerPage)
                                .build())
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                if (response == null) {
                    return Optional.empty();
                }

                PexelsSearchResponseDto searchResponse = objectMapper.readValue(response, PexelsSearchResponseDto.class);
                return Optional.of(searchResponse);

            } catch (WebClientResponseException e) {
                log.error("Erreur lors de l'appel API Pexels: Status {}, Message: {}", e.getStatusCode(), e.getMessage());
                return Optional.empty();
            } catch (Exception e) {
                log.error("Erreur lors de la recherche de photos pour le mot-clé: {}", keyword, e);
                return Optional.empty();
            }
        }

        @Override
        public Optional<PexelsPhotoDto> getPhotoById(Long photoId) {
            try {
                String response = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("photos/{id}")
                                .build(photoId))
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                if (response == null) {
                    return Optional.empty();
                }

                PexelsPhotoDto photo = objectMapper.readValue(response, PexelsPhotoDto.class);
                return Optional.of(photo);

            } catch (WebClientResponseException e) {
                log.error("Erreur lors de la récupération de la photo avec l'ID: {}, Status {}", photoId, e.getStatusCode());
                return Optional.empty();
            } catch (Exception e) {
                log.error("Erreur lors de la récupération de la photo avec l'ID: {}", photoId, e);
                return Optional.empty();
            }
        }

        @Override
        public String findBestImageForArticle(String articleDesignation) {
            if (articleDesignation == null || articleDesignation.trim().isEmpty()) {
                return null;
            }

            // Nettoyer la désignation pour améliorer la recherche
            String cleanedDesignation = cleanKeywordForSearch(articleDesignation);

            // Essayer d'abord avec la désignation complète
            String imageUrl = getPhotoUrl(cleanedDesignation, ImageSize.MEDIUM);

            // Si pas d'image trouvée, essayer avec le premier mot
            if (imageUrl == null) {
                String[] words = cleanedDesignation.split("\\s+");
                if (words.length > 0) {
                    imageUrl = getPhotoUrl(words[0], ImageSize.MEDIUM);
                }
            }

            // Si toujours pas d'image, essayer des termes génériques
            if (imageUrl == null) {
                List<String> fallbackTerms = Arrays.asList("product", "item", "object", "tool");
                for (String term : fallbackTerms) {
                    imageUrl = getPhotoUrl(term, ImageSize.MEDIUM);
                    if (imageUrl != null) {
                        break;
                    }
                }
            }

            return imageUrl;
        }

        @Override
        public Optional<PexelsPhotoDto> getPhotoWithAllSizes(String keyword) {
            try {
                Optional<PexelsSearchResponseDto> searchResult = searchPhotos(keyword, 1, 1);

                if (searchResult.isPresent() &&
                        searchResult.get().getPhotos() != null &&
                        !searchResult.get().getPhotos().isEmpty()) {

                    return Optional.of(searchResult.get().getPhotos().get(0));
                }

                return Optional.empty();

            } catch (Exception e) {
                log.error("Erreur lors de la récupération de la photo avec toutes les tailles pour: {}", keyword, e);
                return Optional.empty();
            }
        }

    /**
     * Extrait l'URL de l'image selon la taille demandée
     */
    private String extractUrlBySize(PexelsPhotoDto photo, ImageSize imageSize) {
        if (photo.getSrc() == null) {
            return null;
        }

        switch (imageSize) {
            case ORIGINAL:
                return photo.getSrc().getOriginal();
            case LARGE2X:
                return photo.getSrc().getLarge2x();
            case LARGE:
                return photo.getSrc().getLarge();
            case MEDIUM:
                return photo.getSrc().getMedium();
            case SMALL:
                return photo.getSrc().getSmall();
            case PORTRAIT:
                return photo.getSrc().getPortrait();
            case LANDSCAPE:
                return photo.getSrc().getLandscape();
            case TINY:
                return photo.getSrc().getTiny();
            default:
                return photo.getSrc().getMedium();
        }
    }

        /**
         * Nettoie le mot-clé pour améliorer la recherche
         */
        private String cleanKeywordForSearch(String keyword) {
            if (keyword == null) {
                return "";
            }

            // Supprimer les caractères spéciaux et normaliser
            return keyword.trim()
                    .toLowerCase()
                    .replaceAll("[^a-zA-Z0-9\\s]", "")
                    .replaceAll("\\s+", " ")
                    .trim();
        }







////
  }


