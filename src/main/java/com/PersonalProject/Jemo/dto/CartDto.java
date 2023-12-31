package com.PersonalProject.Jemo.dto;

import com.PersonalProject.Jemo.model.Cart;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class CartDto {

    private Long id;

    private BigDecimal cardNumber;


    private String cardholderName;


    private Instant expirationDate;


    private Integer cvv;

    private UserDto user;





    public static CartDto fromEntity(Cart cart){
        if(cart == null){
            return null;
        }
        return CartDto.builder()
                .id(cart.getId())
                .cardNumber(cart.getCardNumber())
                .cardholderName(cart.getCardholderName())
                .expirationDate(cart.getExpirationDate())
                .cvv(cart.getCvv())
                .user(UserDto.fromEntity(cart.getUser()))
                .build();
    }

    public static Cart toEntity(CartDto cartDto){
        if (cartDto == null){
            return null;
        }
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setCardNumber(cartDto.getCardNumber());
        cart.setCardholderName(cartDto.getCardholderName());
        cart.setExpirationDate(cartDto.getExpirationDate());
        cart.setCvv(cartDto.getCvv());
        cart.setUser(UserDto.toEntity(cartDto.getUser()));
        return cart;
    }

}
