package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.MvtStkApi;
import com.PersonalProject.Jemo.dto.MvtStkDto;
import com.PersonalProject.Jemo.services.MvtStkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MvtStkController implements MvtStkApi {

    private final MvtStkService mvtStkService;
    public MvtStkController(MvtStkService mvtStkService) {
        super();
        this.mvtStkService = mvtStkService;
    }

    @Override
    public  ResponseEntity<BigDecimal> stockReelProduct(Long id) {
        return  ResponseEntity.ok(mvtStkService.stockReelProduct(id));
    }

    @Override
    public  ResponseEntity<List<MvtStkDto>> mvtStkProduct(Long id) {
        return  ResponseEntity.ok(mvtStkService.mvtStkProduct(id));
    }

    @Override
    public  ResponseEntity<MvtStkDto> entryStock(MvtStkDto mvtStkDto) {
        return  ResponseEntity.ok(mvtStkService.entryStock(mvtStkDto));
    }

    @Override
    public  ResponseEntity<MvtStkDto> exitStock(MvtStkDto mvtStkDto) {
        return  ResponseEntity.ok(mvtStkService.exitStock(mvtStkDto));
    }

    @Override
    public  ResponseEntity<MvtStkDto> correctionStockPos(MvtStkDto mvtStkDto) {
        return  ResponseEntity.ok(mvtStkService.correctionStockPos(mvtStkDto));
    }

    @Override
    public ResponseEntity<MvtStkDto> correctionStockNeg(MvtStkDto mvtStkDto) {
        return  ResponseEntity.ok(mvtStkService.correctionStockNeg(mvtStkDto));
    }
}
