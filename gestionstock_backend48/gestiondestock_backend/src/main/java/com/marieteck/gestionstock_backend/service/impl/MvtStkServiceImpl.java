package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.MvtStkDto;
import com.marieteck.gestionstock_backend.service.MvtStkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {
    @Override
    public BigDecimal stockReelArticle(Long idArticle) {
        return null;
    }

    @Override
    public List<MvtStkDto> mvtStkArticle(Long idArticle) {
        return List.of();
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto dto) {
        return null;
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto dto) {
        return null;
    }

    @Override
    public MvtStkDto correctionStockPos(MvtStkDto dto) {
        return null;
    }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto dto) {
        return null;
    }
}
