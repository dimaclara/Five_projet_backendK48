package com.marieteck.gestionstock_backend.service.impl;

import com.marieteck.gestionstock_backend.dto.MvtStkDto;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.model.TypeMvtStk;
import com.marieteck.gestionstock_backend.repository.ArticleRepository;
import com.marieteck.gestionstock_backend.repository.MvtStkRepository;
import com.marieteck.gestionstock_backend.service.MvtStkService;
import com.marieteck.gestionstock_backend.validator.MvtStkValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class MvtStkServiceImpl implements MvtStkService {

    private MvtStkRepository mvtStkRepository;
    private ArticleRepository articleRepository;

    @Override
    public BigDecimal stockReelArticle(Long idArticle) {
        if (idArticle == null) {
            log.warn("ID article is NULL");
            return BigDecimal.valueOf(-1);
        }
        articleRepository.findById(idArticle);
        return mvtStkRepository.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStkDto> mvtStkArticle(Long idArticle) {
        return mvtStkRepository.findAllByArticleId(idArticle).stream()
                .map(MvtStkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto mvtStkDto) {
        return entreePositive(mvtStkDto, TypeMvtStk.ENTREE);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto mvtStkDto) {
        return sortieNegative(mvtStkDto, TypeMvtStk.SORTIE);
    }

    @Override
    public MvtStkDto correctionStockPos(MvtStkDto mvtStkDto) {
        return entreePositive(mvtStkDto,TypeMvtStk.CORRECTION_POS);
    }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto dto) {
        return sortieNegative(dto, TypeMvtStk.CORRECTION_NEG);
    }


    private MvtStkDto sortieNegative(MvtStkDto mvtStkDto, TypeMvtStk typeMvtStk) {
        List<String> errors = MvtStkValidator.validate(mvtStkDto);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", mvtStkDto);
            throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }
        mvtStkDto.setQuantite(
                BigDecimal.valueOf(
                        Math.abs(mvtStkDto.getQuantite().doubleValue()) * -1
                )
        );
        mvtStkDto.setTypeMvt(typeMvtStk);
        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(mvtStkDto))
        );
    }

    private MvtStkDto entreePositive(MvtStkDto mvtStkDto, TypeMvtStk typeMvtStk) {
        List<String> errors = MvtStkValidator.validate(mvtStkDto);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}",mvtStkDto );
            throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }
        mvtStkDto.setQuantite(
                BigDecimal.valueOf(
                        Math.abs(mvtStkDto.getQuantite().doubleValue())
                )
        );
       mvtStkDto.setTypeMvt(typeMvtStk);
        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(mvtStkDto))
        );
    }



}
