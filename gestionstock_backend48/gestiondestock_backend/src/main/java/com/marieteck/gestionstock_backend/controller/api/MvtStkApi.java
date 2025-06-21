package com.marieteck.gestionstock_backend.controller.api;

import com.marieteck.gestionstock_backend.dto.MvtStkDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

import static com.marieteck.gestionstock_backend.utils.Constants.APP_ROOT;

public interface MvtStkApi {

    @GetMapping(value = APP_ROOT + "/mvtStk/stockReel/{idArticle}", consumes = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal stockReelArticle(@PathVariable("idArticle") Long idArticle);

    @GetMapping(value = APP_ROOT + "/mvtStk/filter/article/{idArticle}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<MvtStkDto> mvtStkArticle(@PathVariable("idArticle") Long idArticle);


    @PostMapping(value = APP_ROOT + "/mvtStk/entree/",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto entreeStock(@RequestBody MvtStkDto mvtStkDto);

    @PostMapping (value = APP_ROOT + "/mvtStk/sortie/",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto sortieStock(@RequestBody MvtStkDto mvtStkDto);

    @PostMapping(value = APP_ROOT + "/mvtstk/correctionPos/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto correctionStockPos(@RequestBody MvtStkDto mvtStkDto);

    @PostMapping(value = APP_ROOT + "/mvtStk/correctionNeg/",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStkDto correctionStockNeg(@RequestBody MvtStkDto mvtStkDto);

}
