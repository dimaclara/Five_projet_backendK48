package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.CommandeClientDto;
import com.marieteck.gestionstock_backend.dto.LigneCommandeClientDto;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.model.CommandeClient;
import com.marieteck.gestionstock_backend.model.EtatCommande;
import com.marieteck.gestionstock_backend.repository.CommandeClientRepository;
import com.marieteck.gestionstock_backend.service.CommandeClientService;
import com.marieteck.gestionstock_backend.validator.CommandeClientValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if(!errors.isEmpty()){
            log.error("commandeClient is not valide  {}", commandeClientDto);
            throw new InvalidEntityException("Commande client is not available", ErrorCodes.COMMANDE_CLIENT_ALREADY_IN_USE, errors);

        }
        CommandeClient commandeClientSaved = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));
        return CommandeClientDto.fromEntity(commandeClientSaved);

    }

    @Override
    public List<CommandeClientDto> findAll() {
        return List.of();
    }

    public CommandeClientDto findById(Long id) {
        return null;
    }

    @Override
    public CommandeClientDto updateArticle(Long idCommande, Long idLigneCommande, Long newIdArticle) {
        return null;
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        return null;
    }

    @Override
    public CommandeClientDto updateEtatCommande(Long idCommande, EtatCommande etatCommande) {
        return null;
    }


    @Override
    public List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Long idCommande) {
        return List.of();
    }


    @Override
    public CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande) {
        return null;
    }

    @Override
    public CommandeClientDto updateClient(Long idCommande, Long idClient) {
        return null;
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Long idCommande, Long idLigneCommande, BigDecimal quantite) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
