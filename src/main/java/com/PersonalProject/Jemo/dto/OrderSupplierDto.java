package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.OrderStatu;
import com.PersonalProject.Jemo.model.OrderSupplier;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class OrderSupplierDto {

    private Long id;

    private String code;

    private Instant dateOrder;

    private OrderStatu orderStatu;

    private SupplierDto supplierDto;

    private List<ItemOrderSupplierDto> itemOrderSupplierDto;


    public static OrderSupplierDto fromEntity(OrderSupplier orderSupplier){
        if (orderSupplier == null){
            return null;
        }
        return OrderSupplierDto.builder()
                .id(orderSupplier.getId())
                .code(orderSupplier.getCode())
                .dateOrder(orderSupplier.getDateOrder())
                .orderStatu(orderSupplier.getOrderStatu())
                .supplierDto(SupplierDto.fromEntity(orderSupplier.getSupplier())).build();
    }

    public static OrderSupplier toEntity(OrderSupplierDto orderSupplierDto){
        if (orderSupplierDto == null){
            return null;
        }
        OrderSupplier orderSupplier = new OrderSupplier();
        orderSupplier.setId(orderSupplierDto.getId());
        orderSupplier.setCode(orderSupplierDto.getCode());
        orderSupplier.setDateOrder(orderSupplierDto.getDateOrder());
        orderSupplier.setOrderStatu(orderSupplierDto.getOrderStatu());
        orderSupplier.setSupplier(SupplierDto.toEntity(orderSupplierDto.getSupplierDto()));
        return orderSupplier;
    }

    public boolean isOrderDELIVERED() {
        return OrderStatu.DELIVERED.equals(this.orderStatu);
    }
}
