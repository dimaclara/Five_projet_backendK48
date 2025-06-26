package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.controller.api.MvtStkApi;
import com.marieteck.gestionstock_backend.dto.MvtStkDto;
import com.marieteck.gestionstock_backend.service.MvtStkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MvtStkController implements MvtStkApi {

    private final MvtStkService mvtStkService;
    @Autowired
    public MvtStkController(MvtStkService mvtStkService) {
        this.mvtStkService = mvtStkService;
    }


    @Override
    public BigDecimal stockReelArticle(Long idArticle) {
        return mvtStkService.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStkDto> mvtStkArticle(Long idArticle) {
        return mvtStkService.mvtStkArticle(idArticle);
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto mvtStkDto) {
        return mvtStkService.entreeStock(mvtStkDto);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto mvtStkDto) {
        return mvtStkService.sortieStock(mvtStkDto);
    }

    @Override
    public MvtStkDto correctionStockPos(MvtStkDto mvtStkDto) {
        return mvtStkService.correctionStockPos(mvtStkDto);
    }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto mvtStkDto) {
        return mvtStkService.correctionStockNeg(mvtStkDto);
    }
}
