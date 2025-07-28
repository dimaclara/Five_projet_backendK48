package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.ArticleDto;
import com.marieteck.gestionstock_backend.dto.PexelsPhotoDto;
import com.marieteck.gestionstock_backend.model.ImageSize;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ArticleImageService {
    /**
     * Met à jour automatiquement l'image d'un article existant
     * @param articleId L'ID de l'article
     * @param forceUpdate Force la mise à jour même si une image existe déjà
     * @return L'article mis à jour ou null si erreur
     */
    ArticleDto updateArticleImage(Long articleId, boolean forceUpdate);

    /**
     * Met à jour les images de tous les articles sans image
     * @return Le nombre d'articles mis à jour
     */
    int updateAllArticlesWithoutImage();

    /**
     * Permet à l'utilisateur de choisir parmi plusieurs images pour un article
     * @param articleId L'ID de l'article
     * @param imageOptions Le nombre d'options d'images à proposer (max 10)
     * @return Liste des photos disponibles
     */
    List<PexelsPhotoDto> getImageOptionsForArticle(Long articleId, int imageOptions);

    /**
     * Applique une image spécifique à un article
     * @param articleId L'ID de l'article
     * @param photoId L'ID de la photo Pexels
     * @param imageSize La taille d'image désirée
     * @return L'article mis à jour
     */
    ArticleDto setSpecificImageToArticle(Long articleId, Long photoId, ImageSize imageSize);

    /**
     * Régénère l'image d'un article avec de nouveaux critères de recherche
     * @param articleId L'ID de l'article
     * @param customKeyword Un mot-clé personnalisé pour la recherche
     * @return L'article mis à jour
     */
    ArticleDto regenerateArticleImage(Long articleId, String customKeyword);

    /**
     * Obtient des statistiques sur l'utilisation des images
     * @return Map contenant les statistiques
     */
    ImageStatistics getImageStatistics();

    /**
     * Classe pour encapsuler les statistiques des images
     */
    class ImageStatistics {
        private final long totalArticles;
        private final long articlesWithImages;
        private final long articlesWithoutImages;
        private final double imagePercentage;

        public ImageStatistics(long totalArticles, long articlesWithImages,
                               long articlesWithoutImages, double imagePercentage) {
            this.totalArticles = totalArticles;
            this.articlesWithImages = articlesWithImages;
            this.articlesWithoutImages = articlesWithoutImages;
            this.imagePercentage = imagePercentage;
        }

        // Getters
        public long getTotalArticles() { return totalArticles; }
        public long getArticlesWithImages() { return articlesWithImages; }
        public long getArticlesWithoutImages() { return articlesWithoutImages; }
        public double getImagePercentage() { return imagePercentage; }
    }

}
