package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.OrderUser;
import com.PersonalProject.Jemo.model.OrderStatu;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class OrderUserDto {

    private Long id;

    private String codeOrder;

    private Instant dateOrder;

    private Integer total;

    private OrderStatu orderStatu;

    private UserDto userDto;

    private CartDto cartDto;

    private List<ItemOrderUserDto> itemOrderUserDtos;

    public static OrderUserDto fromEntity(OrderUser orderUser){
        if (orderUser == null){
            return null;
        }
        return OrderUserDto.builder()
                .id(orderUser.getId())
                .codeOrder(orderUser.getCodeOrder())
                .dateOrder(orderUser.getDateOrder())
                .total(orderUser.getTotal())
                .orderStatu(orderUser.getOrderStatu())
                .userDto(UserDto.fromEntity(orderUser.getUser()))
                .cartDto(CartDto.fromEntity(orderUser.getCart()))
                .build();

    }

    public static OrderUser toEntity(OrderUserDto orderUserDto){
        if (orderUserDto == null){
            return null;
        }
        OrderUser orderUser = new OrderUser();
        orderUser.setId(orderUserDto.getId());
        orderUser.setCodeOrder(orderUserDto.getCodeOrder());
        orderUser.setDateOrder(orderUserDto.getDateOrder());
        orderUser.setTotal(orderUserDto.getTotal());
        orderUser.setOrderStatu(orderUserDto.getOrderStatu());
        orderUser.setUser(UserDto.toEntity(orderUserDto.getUserDto()));
        orderUser.setCart(CartDto.toEntity(orderUserDto.getCartDto()));
        return orderUser;
    }

    public boolean isOrderDELIVERED() {
        return OrderStatu.DELIVERED.equals(this.orderStatu);
    }

}
