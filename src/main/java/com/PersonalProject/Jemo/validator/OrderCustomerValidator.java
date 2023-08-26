package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.OrderCustomerDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderCustomerValidator {

    public static List<String> validator(OrderCustomerDto orderCustomerDto){
        List<String> errors = new ArrayList<>();
        if (orderCustomerDto == null){
            errors.add("Please fill in the customer order fields");
        } else {
            if (StringUtils.hasLength(orderCustomerDto.getCodeOrder())){
                errors.add("Please fill in the code order field");
            }
            if (orderCustomerDto.getDateOrder() == null) {
                errors.add("Please fill in the date order field");
            }
            if (orderCustomerDto.getTotal() == 0){
                errors.add("Please fill in the Total price field");
            }
            if (StringUtils.hasLength(orderCustomerDto.getOrderStatu().toString())){
                errors.add("Please fill in the the order status");
            }
            if (orderCustomerDto.getCustomerDto() == null || orderCustomerDto.getCustomerDto().getId() == null){
                errors.add("Please fill in the customer");
            }
            if (orderCustomerDto.getCartDto() == null || orderCustomerDto.getCartDto().getId() == null){
                errors.add("Please fill in the customer");
            }
        }
        return errors;
    }
}
