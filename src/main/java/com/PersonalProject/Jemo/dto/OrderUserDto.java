package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.OrderUser;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class OrderUserDto {

    private String id;

    private Instant dateOrder;

    private Integer total;

    private String orderStatus;

    private UserDto userDto;

    private CartDto cartDto;

    private List<ItemOrderUserDto> itemOrderUserDtos;

    public static OrderUserDto fromEntity(OrderUser orderUser) {
        if (orderUser == null) {
            return null;
        }
        return OrderUserDto.builder()
                .id(orderUser.getId())
                .dateOrder(orderUser.getDateOrder())
                .total(orderUser.getTotal())
                .orderStatus(orderUser.getOrderStatus())
                .userDto(UserDto.fromEntity(orderUser.getUser()))
                .cartDto(CartDto.fromEntity(orderUser.getCart()))
                .build();

    }

    public static OrderUser toEntity(OrderUserDto orderUserDto) {
        if (orderUserDto == null) {
            return null;
        }
        OrderUser orderUser = new OrderUser();
        orderUser.setId(orderUserDto.getId());
        orderUser.setDateOrder(orderUserDto.getDateOrder());
        orderUser.setTotal(orderUserDto.getTotal());
        orderUser.setOrderStatus(orderUserDto.getOrderStatus());
        orderUser.setUser(UserDto.toEntity(orderUserDto.getUserDto()));
        orderUser.setCart(CartDto.toEntity(orderUserDto.getCartDto()));
        return orderUser;
    }

    public boolean isOrderDELIVERED() {
    return  Objects.equals(this.orderStatus, "DELIVERED");
    }
}