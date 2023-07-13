package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.CartDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CartValidator {

    public static List<String> validator(CartDto cartDto) {
        List<String> errors = new ArrayList<>();

        if (cartDto == null) {
            errors.add("Please fill in the cart fields");
        } else {
            if (cartDto.getCardNumber().compareTo(BigDecimal.ZERO) == 0) {
                errors.add("Please fill in the card number");
            }
            if (!StringUtils.hasLength(cartDto.getCardholderName())) {
                errors.add("Please fill in the card holder name");
            }
            if (cartDto.getExpirationMonth() == null) {
                errors.add("Please fill in the card expiration month");
            }
            if (cartDto.getExpirationYear() == null) {
                errors.add("Please fill in the card Expiration year");
            }
        }
        return errors;
    }
}
