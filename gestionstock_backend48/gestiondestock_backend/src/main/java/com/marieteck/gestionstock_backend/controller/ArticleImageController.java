package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.ArticleImageApi;
import com.marieteck.gestionstock_backend.dto.ArticleDto;
import com.marieteck.gestionstock_backend.dto.PexelsPhotoDto;
import com.marieteck.gestionstock_backend.model.ImageSize;
import com.marieteck.gestionstock_backend.service.ArticleImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor

public class ArticleImageController implements ArticleImageApi {
    private final ArticleImageService articleImageService;

//    @Override
    public ResponseEntity<ArticleDto> updateArticleImage(Long articleId, boolean forceUpdate) {
        log.info("Mise à jour de l'image pour l'article {}, forceUpdate: {}", articleId, forceUpdate);

        ArticleDto updatedArticle = articleImageService.updateArticleImage(articleId, forceUpdate);

        if (updatedArticle != null) {
            return ResponseEntity.ok(updatedArticle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @Override
    public ResponseEntity<Integer> updateAllArticlesWithoutImage() {
        log.info("Mise à jour en masse des images d'articles");

        int updatedCount = articleImageService.updateAllArticlesWithoutImage();

        return ResponseEntity.ok(updatedCount);
    }

//    @Override
    public ResponseEntity<List<PexelsPhotoDto>> getImageOptionsForArticle(Long articleId, int imageOptions) {
        log.info("Récupération des options d'images pour l'article {}, options: {}", articleId, imageOptions);

        List<PexelsPhotoDto> options = articleImageService.getImageOptionsForArticle(articleId, imageOptions);

        if (!options.isEmpty()) {
            return ResponseEntity.ok(options);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

//    @Override
    public ResponseEntity<ArticleDto> setSpecificImageToArticle(Long articleId, Long photoId, ImageSize imageSize) {
        log.info("Application de l'image {} à l'article {} en taille {}", photoId, articleId, imageSize);

        ArticleDto updatedArticle = articleImageService.setSpecificImageToArticle(articleId, photoId, imageSize);

        if (updatedArticle != null) {
            return ResponseEntity.ok(updatedArticle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @Override
    public ResponseEntity<ArticleDto> regenerateArticleImage(Long articleId, String customKeyword) {
        log.info("Régénération de l'image pour l'article {} avec le mot-clé: {}", articleId, customKeyword);

        ArticleDto updatedArticle = articleImageService.regenerateArticleImage(articleId, customKeyword);

        if (updatedArticle != null) {
            return ResponseEntity.ok(updatedArticle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @Override
    public ResponseEntity<ArticleImageService.ImageStatistics> getImageStatistics() {
        log.info("Récupération des statistiques d'images");

        ArticleImageService.ImageStatistics statistics = articleImageService.getImageStatistics();

        return ResponseEntity.ok(statistics);
    }
}
