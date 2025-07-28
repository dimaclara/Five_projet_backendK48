package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.PexelsPhotoDto;
import com.marieteck.gestionstock_backend.dto.PexelsSearchResponseDto;
import com.marieteck.gestionstock_backend.model.ImageSize;

import java.io.InputStream;
import java.util.Optional;


public interface PexelService {


    /**
     * Recherche une photo par mot-clé et retourne l'URL de taille moyenne
     * @param keyword Le mot-clé de recherche
     * @return L'URL de l'image ou null si non trouvée
     */

    String savePhoto(String keyword);


    /**
     * Recherche une photo par mot-clé et retourne l'URL de la taille spécifiée
     * @param keyword Le mot-clé de recherche
     * @param imageSize La taille d'image désirée
     * @return L'URL de l'image ou null si non trouvée
     */
    String getPhotoUrl(String keyword, ImageSize imageSize);


    /**
     * Recherche des photos par mot-clé
     * @param keyword Le mot-clé de recherche
     * @param page Le numéro de page (par défaut 1)
     * @param perPage Le nombre de résultats par page (par défaut 10, max 80)
     * @return La réponse de recherche Pexels
     */
    Optional<PexelsSearchResponseDto> searchPhotos(String keyword, Integer page, Integer perPage);

    /**
     * Obtient une photo spécifique par son ID
     * @param photoId L'ID de la photo
     * @return La photo si trouvée
     */
    Optional<PexelsPhotoDto> getPhotoById(Long photoId);

    /**
     * Recherche automatique d'image pour un article basée sur sa désignation
     * @param articleDesignation La désignation de l'article
     * @return L'URL de l'image la plus appropriée
     */
    String findBestImageForArticle(String articleDesignation);

    /**
     * Obtient plusieurs tailles d'une image pour un mot-clé donné
     * @param keyword Le mot-clé de recherche
     * @return Un objet contenant les URLs de différentes tailles
     */
    Optional<PexelsPhotoDto> getPhotoWithAllSizes(String keyword);


}
