package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.ArticleDto;
import com.marieteck.gestionstock_backend.dto.PexelsPhotoDto;
import com.marieteck.gestionstock_backend.dto.PexelsSearchResponseDto;
import com.marieteck.gestionstock_backend.model.ImageSize;
import com.marieteck.gestionstock_backend.repository.ArticleRepository;
import com.marieteck.gestionstock_backend.service.ArticleImageService;
import com.marieteck.gestionstock_backend.service.ArticleService;
import com.marieteck.gestionstock_backend.service.PexelService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.marieteck.gestionstock_backend.model.ImageSize.*;
@Service
@Slf4j
@AllArgsConstructor

public class ArticleImageServiceImpl implements ArticleImageService {

    private  ArticleService articleService;
    private  PexelService pexelService;
    private  ArticleRepository articleRepository;

//    @Override
//    @Transactional
    public ArticleDto updateArticleImage(Long articleId, boolean forceUpdate) {
        try {
            ArticleDto article = articleService.findById(articleId);
            if (article == null) {
                log.error("Article avec l'ID {} non trouvé", articleId);
                return null;
            }

            // Vérifier si une mise à jour est nécessaire
            if (!forceUpdate && article.getPhoto() != null && !article.getPhoto().trim().isEmpty()) {
                log.info("Article {} a déjà une image et forceUpdate=false", articleId);
                return article;
            }

            String imageUrl = pexelService.findBestImageForArticle(article.getDesignation());
            if (imageUrl != null) {
                article.setPhoto(imageUrl);
                ArticleDto updatedArticle = articleService.save(article);
                log.info("Image mise à jour pour l'article {}: {}", articleId, imageUrl);
                return updatedArticle;
            } else {
                log.warn("Aucune image trouvée pour l'article {}", articleId);
                return article;
            }

        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour de l'image pour l'article {}", articleId, e);
            return null;
        }
    }

//    @Override
//    @Transactional
    public int updateAllArticlesWithoutImage() {
        try {
            List<ArticleDto> allArticles = articleService.findAll();
            int updatedCount = 0;

            for (ArticleDto article : allArticles) {
                if (article.getPhoto() == null || article.getPhoto().trim().isEmpty()) {
                    ArticleDto updated = updateArticleImage(article.getId(), false);
                    if (updated != null && updated.getPhoto() != null && !updated.getPhoto().trim().isEmpty()) {
                        updatedCount++;
                    }

                    // Petite pause pour éviter de surcharger l'API Pexels
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }

            log.info("Mise à jour automatique terminée: {} articles mis à jour", updatedCount);
            return updatedCount;

        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour en masse des images", e);
            return 0;
        }
    }

    @Override
    public List<PexelsPhotoDto> getImageOptionsForArticle(Long articleId, int imageOptions) {
        try {
            ArticleDto article = articleService.findById(articleId);
            if (article == null) {
                log.error("Article avec l'ID {} non trouvé", articleId);
                return new ArrayList<>();
            }

            // Limiter le nombre d'options entre 1 et 10
            int validOptions = Math.max(1, Math.min(imageOptions, 10));

            Optional<PexelsSearchResponseDto> searchResult = pexelService.searchPhotos(
                    article.getDesignation(), 1, validOptions);

            if (searchResult.isPresent() && searchResult.get().getPhotos() != null) {
                return searchResult.get().getPhotos();
            } else {
                return new ArrayList<>();
            }

        } catch (Exception e) {
            log.error("Erreur lors de la récupération des options d'images pour l'article {}", articleId, e);
            return new ArrayList<>();
        }
    }

//    @Override
//    @Transactional
    public ArticleDto setSpecificImageToArticle(Long articleId, Long photoId, ImageSize imageSize) {
        try {
            ArticleDto article = articleService.findById(articleId);
            if (article == null) {
                log.error("Article avec l'ID {} non trouvé", articleId);
                return null;
            }

            Optional<PexelsPhotoDto> photo = pexelService.getPhotoById(photoId);
            if (photo.isPresent()) {
                String imageUrl = extractUrlBySize(photo.get(), imageSize);
                if (imageUrl != null) {
                    article.setPhoto(imageUrl);
                    ArticleDto updatedArticle = articleService.save(article);
                    log.info("Image spécifique appliquée à l'article {}: photo {} en taille {}",
                            articleId, photoId, imageSize);
                    return updatedArticle;
                }
            }

            log.warn("Impossible d'appliquer la photo {} à l'article {}", photoId, articleId);
            return article;

        } catch (Exception e) {
            log.error("Erreur lors de l'application de l'image spécifique", e);
            return null;
        }
    }

//    @Override
//    @Transactional
    public ArticleDto regenerateArticleImage(Long articleId, String customKeyword) {
        try {
            ArticleDto article = articleService.findById(articleId);
            if (article == null) {
                log.error("Article avec l'ID {} non trouvé", articleId);
                return null;
            }

            String searchKeyword = (customKeyword != null && !customKeyword.trim().isEmpty())
                    ? customKeyword.trim()
                    : article.getDesignation();

            String imageUrl = pexelService.findBestImageForArticle(searchKeyword);
            if (imageUrl != null) {
                article.setPhoto(imageUrl);
                ArticleDto updatedArticle = articleService.save(article);
                log.info("Image régénérée pour l'article {} avec le mot-clé '{}': {}",
                        articleId, searchKeyword, imageUrl);
                return updatedArticle;
            } else {
                log.warn("Aucune nouvelle image trouvée pour l'article {} avec le mot-clé '{}'",
                        articleId, searchKeyword);
                return article;
            }

        } catch (Exception e) {
            log.error("Erreur lors de la régénération de l'image pour l'article {}", articleId, e);
            return null;
        }
    }

//    @Override
    public ImageStatistics getImageStatistics() {
        try {
            long totalArticles = articleRepository.count();
            long articlesWithImages = articleRepository.countByPhotoIsNotNullAndPhotoNot("");
            long articlesWithoutImages = totalArticles - articlesWithImages;
            double imagePercentage = totalArticles > 0 ? (double) articlesWithImages / totalArticles * 100 : 0;

            return new ImageStatistics(totalArticles, articlesWithImages, articlesWithoutImages, imagePercentage);

        } catch (Exception e) {
            log.error("Erreur lors du calcul des statistiques d'images", e);
            return new ImageStatistics(0, 0, 0, 0);
        }
    }

    /**
     * Méthode utilitaire pour extraire l'URL selon la taille
     */
    private String extractUrlBySize(PexelsPhotoDto photo, ImageSize imageSize) {
        if (photo.getSrc() == null) {
            return null;
        }

        return switch (imageSize) {
            case ORIGINAL -> photo.getSrc().getOriginal();
            case LARGE2X -> photo.getSrc().getLarge2x();
            case LARGE -> photo.getSrc().getLarge();
            case MEDIUM -> photo.getSrc().getMedium();
            case SMALL -> photo.getSrc().getSmall();
            case PORTRAIT -> photo.getSrc().getPortrait();
            case LANDSCAPE -> photo.getSrc().getLandscape();
            case TINY -> photo.getSrc().getTiny();
        };
    }
}
