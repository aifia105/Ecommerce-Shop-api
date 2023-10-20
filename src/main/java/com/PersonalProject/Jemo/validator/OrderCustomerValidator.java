package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.OrderUserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderCustomerValidator {

    public static List<String> validator(OrderUserDto orderUserDto){
        List<String> errors = new ArrayList<>();
        if (orderUserDto == null){
            errors.add("Please fill in the customer order fields");
        } else {
            if (StringUtils.hasLength(orderUserDto.getCodeOrder())){
                errors.add("Please fill in the code order field");
            }
            if (orderUserDto.getDateOrder() == null) {
                errors.add("Please fill in the date order field");
            }
            if (orderUserDto.getTotal() == 0){
                errors.add("Please fill in the Total price field");
            }
            if (StringUtils.hasLength(orderUserDto.getOrderStatu().toString())){
                errors.add("Please fill in the the order status");
            }
            if (orderUserDto.getUserDto() == null || orderUserDto.getUserDto().getId() == null){
                errors.add("Please fill in the customer");
            }
            if (orderUserDto.getCartDto() == null || orderUserDto.getCartDto().getId() == null){
                errors.add("Please fill in the customer");
            }
        }
        return errors;
    }
}
