package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.CommandeClientDto;
import com.marieteck.gestionstock_backend.dto.LigneCommandeClientDto;
import com.marieteck.gestionstock_backend.exception.EntityNotFoundException;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.model.*;
import com.marieteck.gestionstock_backend.repository.ArticleRepository;
import com.marieteck.gestionstock_backend.repository.ClientRepository;
import com.marieteck.gestionstock_backend.repository.CommandeClientRepository;
import com.marieteck.gestionstock_backend.repository.LigneCommandeClientRepository;
import com.marieteck.gestionstock_backend.service.CommandeClientService;
import com.marieteck.gestionstock_backend.validator.CommandeClientValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {

        // faire une validation des attributs
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if(!errors.isEmpty()){
            log.error("commandeClient is not valide  {}", commandeClientDto);
            throw new InvalidEntityException("Commande client is not available", ErrorCodes.COMMANDE_CLIENT_ALREADY_IN_USE, errors);

        }
        // verifier que le client existe avec son ID car une commande fait reference a un client
        Optional<Client> client = clientRepository.findById(commandeClientDto.getClientDto().getId());
        if(!client.isPresent()){
            log.error("client is not found with this id {}", commandeClientDto.getClientDto().getId());

            throw new EntityNotFoundException("no custom with ID" + commandeClientDto.getClientDto().getId() + "was not found");
        }
        // verifier les lignes des articles commandes client il faut verifier les id des articles
        // faire une boucle
        List<String> articleErrors = CommandeClientValidator.validate(commandeClientDto);
        if (commandeClientDto.getLigneCommandeClients() != null) {
            commandeClientDto.getLigneCommandeClients().forEach(ligneCommandeClientDto -> {
                if (ligneCommandeClientDto.getArticleDto() != null) {
                    Optional<Article> article = articleRepository.findById(ligneCommandeClientDto.getCommandeClientDto().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("article with id " + ligneCommandeClientDto.getArticleDto().getId() + " is not found");

                    }  else {
                        articleErrors.add("imposible to register this article with Id NULL " );
                    }
                }

            });

        }
        if (articleErrors .isEmpty()) {
            log.warn("");
            throw new InvalidEntityException("Article is not available in DB",ErrorCodes.COMMANDE_CLIENT_ALREADY_IN_USE,articleErrors );
        }
        // creer une CommandeClient
        //ensuite Creer  ligne de commmande client

        CommandeClient commandeClientSaved = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));
        // verifier que mes lignes commandes ne sont pas null car on peut creer uns commande sans preciser les ligne
        if (commandeClientDto.getLigneCommandeClients() != null){
            commandeClientDto.getLigneCommandeClients().forEach(ligCmdClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
                ligneCommandeClient.setCommandeClient(commandeClientSaved);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(commandeClientSaved);

    }


    public CommandeClientDto findById(Long id) {
        if (id == null) {
            log.error("Custom command id is NULL");
            return null;
        };
            return commandeClientRepository.findById(id)
                    .map(CommandeClientDto:: fromEntity)
                    .orElseThrow(()-> new EntityNotFoundException(
                            "No custom Order found with this id " + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND)

                    );

    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll()
                .stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeClientDto updateArticle(Long idCommande, Long idLigneCommande, Long newIdArticle) {
        return null;
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (code == null) {
            log.error("Custom command code is NULL");
            return null;
        }
        return commandeClientRepository.findByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "No custom no found with this code" + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));

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
        if (id == null) {
            log.error("Custom command id is NULL");
            return ;
        }
        commandeClientRepository.deleteById(id);
    }
}
