package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.CommandeFournisseurApi;
import com.marieteck.gestionstock_backend.dto.CommandeFournisseurDto;
import com.marieteck.gestionstock_backend.service.CommandeFounisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

   private final CommandeFounisseurService commandeFounisseurService;

   @Autowired
   public CommandeFournisseurController(CommandeFounisseurService commandeFounisseurService){
       this.commandeFounisseurService = commandeFounisseurService;
   }
    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        return commandeFounisseurService.save(commandeFournisseurDto);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFounisseurService.findAll();
    }

    @Override
    public CommandeFournisseurDto findById(Long id) {
        return commandeFounisseurService.findById(id);
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        return commandeFounisseurService.findByCode(code);
    }

    @Override
    public void deleteById(Long id) {
       commandeFounisseurService.deleteById(id);

    }
}
