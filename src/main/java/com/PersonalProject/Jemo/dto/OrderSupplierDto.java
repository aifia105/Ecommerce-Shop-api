package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.ItemOrderSupplier;
import com.PersonalProject.Jemo.model.OrderStatu;
import com.PersonalProject.Jemo.model.OrderSupplier;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class OrderSupplierDto {

    private Integer id;

    private String code;

    private Instant dateOrder;

    private OrderStatu orderStatu;

    private SupplierDto supplierDto;

    private List<ItemOrderSupplierDto> itemOrderSupplierDtos;


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

    public boolean isCommandeLivre() {
        return OrderStatu.LIVREE.equals(this.orderStatu);
    }
}
