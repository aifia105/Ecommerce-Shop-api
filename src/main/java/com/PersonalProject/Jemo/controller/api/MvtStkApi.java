package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.MvtStkDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.MVT_STK_ENDPOINT;

@Tag(name = "Stock Management" )
public interface MvtStkApi {


    @GetMapping(value = MVT_STK_ENDPOINT + "/stockReel/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BigDecimal> stockReelProduct(@PathVariable String id);

    @GetMapping(value = MVT_STK_ENDPOINT + "/filter/Product/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<MvtStkDto>> mvtStkProduct(@PathVariable String id);

    @PostMapping(value = MVT_STK_ENDPOINT + "/entryStock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MvtStkDto> entryStock(@RequestBody MvtStkDto mvtStkDto);

    @PostMapping(value = MVT_STK_ENDPOINT + "/exitStock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MvtStkDto> exitStock(@RequestBody MvtStkDto mvtStkDto);

    @PostMapping(value = MVT_STK_ENDPOINT + "/correctionStockPos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MvtStkDto> correctionStockPos(@RequestBody MvtStkDto mvtStkDto);

    @PostMapping(value = MVT_STK_ENDPOINT + "/correctionStockNeg", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MvtStkDto> correctionStockNeg(@RequestBody MvtStkDto mvtStkDto);
}
