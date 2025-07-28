package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.ArticleDto;
import com.marieteck.gestionstock_backend.dto.PexelsPhotoDto;
import com.marieteck.gestionstock_backend.model.ImageSize;
import com.marieteck.gestionstock_backend.service.ArticleImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ArticleImageApi {
    @PutMapping(value = "/articles/{articleId}/image")
    ResponseEntity<ArticleDto> updateArticleImage(
            @PathVariable Long articleId,
            @RequestParam(defaultValue = "false") boolean forceUpdate
    );

    @PostMapping(value = "/articles/images/update-all")
    ResponseEntity<Integer> updateAllArticlesWithoutImage();

    @GetMapping(value =  "/articles/{articleId}/image-options")
    ResponseEntity<List<PexelsPhotoDto>> getImageOptionsForArticle(
            @PathVariable Long articleId,
            @RequestParam(defaultValue = "5") int imageOptions
    );

    @PutMapping(value =  "/articles/{articleId}/image/specific")
    ResponseEntity<ArticleDto> setSpecificImageToArticle(
            @PathVariable Long articleId,
            @RequestParam Long photoId,
            @RequestParam(defaultValue = "MEDIUM") ImageSize imageSize
    );

    @PutMapping(value =  "/articles/{articleId}/image/regenerate")
    ResponseEntity<ArticleDto> regenerateArticleImage(
            @PathVariable Long articleId,
            @RequestParam(required = false) String customKeyword
    );

    @GetMapping(value =  "/articles/images/statistics")
    ResponseEntity<ArticleImageService.ImageStatistics> getImageStatistics();
}
