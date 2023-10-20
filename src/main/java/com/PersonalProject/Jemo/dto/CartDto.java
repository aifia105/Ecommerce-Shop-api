package com.PersonalProject.Jemo.dto;

import com.PersonalProject.Jemo.model.Cart;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;

@Data
@Builder
public class CartDto {

    private Long id;

    private BigDecimal cardNumber;


    private String cardholderName;


    private Month expirationMonth;


    private Year expirationYear;


    private Integer cvv;


    private UserDto userDto;

    public static CartDto fromEntity(Cart cart){
        if(cart == null){
            return null;
        }
        return CartDto.builder()
                .cardNumber(cart.getCardNumber())
                .cardholderName(cart.getCardholderName())
                .expirationMonth(cart.getExpirationMonth())
                .expirationYear(cart.getExpirationYear())
                .cvv(cart.getCvv())
                .userDto(UserDto.fromEntity(cart.getUser()))
                .build();
    }

    public static Cart toEntity(CartDto cartDto){
        if (cartDto == null){
            return null;
        }
        Cart cart = new Cart();
        cart.setCardNumber(cartDto.getCardNumber());
        cart.setCardholderName(cartDto.getCardholderName());
        cart.setExpirationMonth(cartDto.getExpirationMonth());
        cart.setExpirationYear(cartDto.getExpirationYear());
        cart.setCvv(cartDto.getCvv());
        cart.setUser(UserDto.toEntity(cartDto.getUserDto()));
        return cart;
    }

}
