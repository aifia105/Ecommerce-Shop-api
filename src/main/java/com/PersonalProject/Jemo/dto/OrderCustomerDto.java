package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.OrderCustomer;
import com.PersonalProject.Jemo.model.OrderStatu;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class OrderCustomerDto {

    private Long id;

    private String codeOrder;

    private Instant dateOrder;

    private Integer total;

    private OrderStatu orderStatu;

    private CustomerDto customerDto;

    private CartDto cartDto;

    private List<ItemOrderCustomerDto> itemOrderCustomerDtos;

    public static OrderCustomerDto fromEntity(OrderCustomer orderCustomer){
        if (orderCustomer == null){
            return null;
        }
        return OrderCustomerDto.builder()
                .id(orderCustomer.getId())
                .codeOrder(orderCustomer.getCodeOrder())
                .dateOrder(orderCustomer.getDateOrder())
                .total(orderCustomer.getTotal())
                .orderStatu(orderCustomer.getOrderStatu())
                .customerDto(CustomerDto.fromEntity(orderCustomer.getCustomer()))
                .cartDto(CartDto.fromEntity(orderCustomer.getCart()))
                .build();

    }

    public static OrderCustomer toEntity(OrderCustomerDto orderCustomerDto){
        if (orderCustomerDto == null){
            return null;
        }
        OrderCustomer orderCustomer = new OrderCustomer();
        orderCustomer.setId(orderCustomerDto.getId());
        orderCustomer.setCodeOrder(orderCustomerDto.getCodeOrder());
        orderCustomer.setDateOrder(orderCustomerDto.getDateOrder());
        orderCustomer.setTotal(orderCustomerDto.getTotal());
        orderCustomer.setOrderStatu(orderCustomerDto.getOrderStatu());
        orderCustomer.setCustomer(CustomerDto.toEntity(orderCustomerDto.getCustomerDto()));
        orderCustomer.setCart(CartDto.toEntity(orderCustomerDto.getCartDto()));
        return orderCustomer;
    }

    public boolean isOrderDELIVERED() {
        return OrderStatu.DELIVERED.equals(this.orderStatu);
    }

}
