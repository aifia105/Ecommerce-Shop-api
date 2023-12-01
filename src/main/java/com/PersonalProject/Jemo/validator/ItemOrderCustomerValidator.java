package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.ItemOrderUserDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemOrderCustomerValidator {

    public static List<String> validator(ItemOrderUserDto itemOrderUserDto){
        List<String> errors = new ArrayList<>();

        if (itemOrderUserDto == null){
            errors.add("Please fill in the fields");
        } else {
            if (itemOrderUserDto.getProductDto() == null || itemOrderUserDto.getProductDto().getId() == null ){
                errors.add("Please fill in the product field");
            }
            if (itemOrderUserDto.getQuantity().compareTo(BigDecimal.ZERO) == 0){
                errors.add("Please fill in the product quantity field");
            }
            if (itemOrderUserDto.getTotal() == 0){
                errors.add("Please fill in the product unit price field");
            }
        }
        return errors;
    }
}
