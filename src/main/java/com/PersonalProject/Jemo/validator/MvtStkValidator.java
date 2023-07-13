package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.MvtStkDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {
    public static List<String> validator(MvtStkDto mvtStkDto){
        List<String> errors = new ArrayList<>();
        if (mvtStkDto == null){
            errors.add("Please fill in the stock movement fields");
        } else {
            if (mvtStkDto.getDateMvt() == null){
                errors.add("Please fill in the movement date field");
            }
            if (mvtStkDto.getQuantity() == null || mvtStkDto.getQuantity().compareTo(BigDecimal.ZERO) == 0){
                errors.add("Please fill in the product quantity field");
            }
            if (mvtStkDto.getProductDto() == null || mvtStkDto.getProductDto().getId() == null){
                errors.add("Please fill in the product field");
            }
            if (!StringUtils.hasLength(mvtStkDto.getTypeMvt().toString())){
                errors.add("Please fill in the movement type field");
            }
        }
        return errors;
    }
}
