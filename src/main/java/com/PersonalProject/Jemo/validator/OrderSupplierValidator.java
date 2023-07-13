package com.PersonalProject.Jemo.validator;
import com.PersonalProject.Jemo.dto.OrderSupplierDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderSupplierValidator {

    public static List<String> validator(OrderSupplierDto orderSupplierDto){
        List<String> errors = new ArrayList<>();
        if (orderSupplierDto == null){
            errors.add("Please fill in the customer order fields");
        } else {
            if (StringUtils.hasLength(orderSupplierDto.getCode())){
                errors.add("Please fill in the code order field");
            }
            if (orderSupplierDto.getDateOrder() == null) {
                errors.add("Please fill in the date order field");
            }
            if (StringUtils.hasLength(orderSupplierDto.getOrderStatu().toString())){
                errors.add("Please fill in the the order status");
            }
            if (orderSupplierDto.getSupplierDto() == null || orderSupplierDto.getSupplierDto().getId() == null){
                errors.add("Please fill in the supplier");
            }
        }
        return errors;
    }
}
