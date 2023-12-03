package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.MvtStkDto;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.model.TypeMvtStk;
import com.PersonalProject.Jemo.repository.MvtStkRepository;
import com.PersonalProject.Jemo.services.MvtStkService;
import com.PersonalProject.Jemo.services.ProductService;
import com.PersonalProject.Jemo.validator.MvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {

    private final MvtStkRepository mvtStkRepository;
    private final ProductService productService;

    @Autowired
    public MvtStkServiceImpl(MvtStkRepository mvtStkRepository, ProductService productService) {
        super();
        this.mvtStkRepository = mvtStkRepository;
        this.productService = productService;
    }

    @Override
    public BigDecimal stockReelProduct(Long id) {
        if(id == null){
            log.warn("Id product is null");
            return BigDecimal.valueOf(-1);
        }
        productService.findById(id);
        return mvtStkRepository.stockReelProduct(id);
    }

    @Override
    public List<MvtStkDto> mvtStkProduct(Long id) {
        if (id == null){
            log.warn("Id product is null");
        }
        return mvtStkRepository.findAllByProductId(id).stream()
                .map(MvtStkDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public MvtStkDto entryStock(MvtStkDto mvtStkDto) {
        return entryPos(mvtStkDto, TypeMvtStk.ENTRY);
    }

    @Override
    public MvtStkDto exitStock(MvtStkDto mvtStkDto) {
        return exitNeg(mvtStkDto, TypeMvtStk.EXIT);
    }

    @Override
    public MvtStkDto correctionStockPos(MvtStkDto mvtStkDto) {
        return entryPos(mvtStkDto, TypeMvtStk.CORRECTION_POS);
    }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto mvtStkDto) {
        return exitNeg(mvtStkDto, TypeMvtStk.CORRECTION_NEG);
    }

    private MvtStkDto entryPos(MvtStkDto mvtStkDto, TypeMvtStk typeMvtStk){
        List<String> errors = MvtStkValidator.validator(mvtStkDto);
        if (!errors.isEmpty()){
            log.error("Mvt stk invalid {}",mvtStkDto);
            throw new EntityNotValidException("Customer invalid", ErrorCodes.MVT_STK_NOT_VALID,errors);
        }
        mvtStkDto.setQuantity(
                BigDecimal.valueOf(
                        Math.abs(mvtStkDto.getQuantity().doubleValue())
                )
        );
        mvtStkDto.setTypeMvt(typeMvtStk);
        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(mvtStkDto))
        );
    }
    private MvtStkDto exitNeg(MvtStkDto mvtStkDto, TypeMvtStk typeMvtStk){
        List<String> errors = MvtStkValidator.validator(mvtStkDto);
        if (!errors.isEmpty()){
            log.error("Mvt stk invalid {}",mvtStkDto);
            throw new EntityNotValidException("Customer invalid", ErrorCodes.MVT_STK_NOT_VALID,errors);
        }
        mvtStkDto.setQuantity(
                BigDecimal.valueOf(
                        Math.abs(mvtStkDto.getQuantity().doubleValue()) * -1
                )
        );
        mvtStkDto.setTypeMvt(typeMvtStk);
        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(mvtStkDto))
        );
    }
}
