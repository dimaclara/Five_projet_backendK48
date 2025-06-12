package com.marieteck.gestionstock_backend.service;

import com.marieteck.gestionstock_backend.dto.CommandeClientDto;
import com.marieteck.gestionstock_backend.dto.LigneCommandeClientDto;
import com.marieteck.gestionstock_backend.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {
    CommandeClientDto save(CommandeClientDto dto);

    CommandeClientDto updateEtatCommande(Long idCommande, EtatCommande etatCommande);

    CommandeClientDto updateQuantiteCommande(Long idCommande, Long idLigneCommande, BigDecimal quantite);

    CommandeClientDto updateClient(Long idCommande, Long idClient);

    CommandeClientDto updateArticle(Long idCommande, Long idLigneCommande, Long newIdArticle);

    // Delete article ==> delete LigneCommandeClient
    CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande);

    CommandeClientDto findById(Long id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();

    List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Long idCommande);

    void deleteById(Long id);

}
