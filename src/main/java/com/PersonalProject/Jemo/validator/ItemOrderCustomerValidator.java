package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.ItemOrderCustomerDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemOrderCustomerValidator {

    public static List<String> validator(ItemOrderCustomerDto itemOrderCustomerDto){
        List<String> errors = new ArrayList<>();

        if (itemOrderCustomerDto == null){
            errors.add("Please fill in the fields");
        } else {
            if (itemOrderCustomerDto.getProductDto() == null || itemOrderCustomerDto.getProductDto().getId() == null ){
                errors.add("Please fill in the product field");
            }
            if (itemOrderCustomerDto.getQuantity().compareTo(BigDecimal.ZERO) == 0){
                errors.add("Please fill in the product quantity field");
            }
            if (itemOrderCustomerDto.getUnit_price() == 0){
                errors.add("Please fill in the product unit price field");
            }
        }
        return errors;
    }
}
