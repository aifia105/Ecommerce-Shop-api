package com.PersonalProject.Jemo.validator;
import com.PersonalProject.Jemo.dto.ItemOrderSupplierDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemOrderSupplierValidator {
    public static List<String> validator(ItemOrderSupplierDto itemOrderSupplierDto){

        List<String> errors = new ArrayList<>();

        if ( itemOrderSupplierDto == null){
            errors.add("Please fill in the fields");
        } else {
            if (itemOrderSupplierDto.getProductDto() == null){
                errors.add("Please fill in the product field");
            }
            if (itemOrderSupplierDto.getQuantity().compareTo(BigDecimal.ZERO) == 0){
                errors.add("Please fill in the product quantity field");
            }
            if (itemOrderSupplierDto.getUnit_price() == 0){
                errors.add("Please fill in the product unit price field");
            }
        }
        return errors;
    }
}
