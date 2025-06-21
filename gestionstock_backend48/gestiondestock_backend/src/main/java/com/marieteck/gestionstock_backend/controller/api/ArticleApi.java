package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.ArticleDto;
import com.marieteck.gestionstock_backend.dto.LigneCommandeClientDto;
import com.marieteck.gestionstock_backend.dto.LigneCommandeFournisseurDto;
import com.marieteck.gestionstock_backend.dto.LigneVenteDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;

public interface ArticleApi {

    @PostMapping(value =APP_ROOT + "/article/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto save(@RequestBody ArticleDto articleDto);

     @GetMapping(value = APP_ROOT + "/article/{idArcticle}",produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findById(@PathVariable("idArticle") Long id);

    @GetMapping(value = APP_ROOT + "/article/all",produces = MediaType.APPLICATION_JSON_VALUE)
     List<ArticleDto> findAll();

    @GetMapping(value = APP_ROOT + "/article/codeArticle",produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @DeleteMapping(value = APP_ROOT  + "/article/{idArticle}")
    void deleteById(@PathVariable("idArticle") Long id);
}
