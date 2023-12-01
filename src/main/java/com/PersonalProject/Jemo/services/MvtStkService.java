package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.MvtStkDto;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkService {

    BigDecimal stockReelProduct(String  id);
    List<MvtStkDto> mvtStkProduct(String  id);
    MvtStkDto entryStock(MvtStkDto mvtStkDto);
    MvtStkDto exitStock(MvtStkDto mvtStkDto);
    MvtStkDto correctionStockPos(MvtStkDto mvtStkDto);
    MvtStkDto correctionStockNeg(MvtStkDto mvtStkDto);



}
