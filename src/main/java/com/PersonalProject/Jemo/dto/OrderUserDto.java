package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.ItemOrderUser;
import com.PersonalProject.Jemo.model.OrderUser;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Collections;
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
                .itemOrderUserDtos(Collections.singletonList(ItemOrderUserDto.fromEntity((ItemOrderUser) orderUser.getItemOrderUsers())))
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
        orderUser.setItemOrderUsers(Collections.singletonList(ItemOrderUserDto.toEntity((ItemOrderUserDto) orderUserDto.getItemOrderUserDtos())));
        return orderUser;
    }

    public boolean isOrderDELIVERED() {
    return  Objects.equals(this.orderStatus, "DELIVERED");
    }
}