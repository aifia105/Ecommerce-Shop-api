package com.PersonalProject.Jemo.dto;

import com.PersonalProject.Jemo.model.ItemOrderUser;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemOrderUserDto {

    private Long id;

    private ProductDto productDto;

    private BigDecimal quantity;

    private Integer total;


    public static ItemOrderUserDto fromEntity(ItemOrderUser itemOrderUser){
        if (itemOrderUser == null){
            return null;
        }
        return ItemOrderUserDto.builder()
                .id(itemOrderUser.getId())
                .productDto(ProductDto.formEntity(itemOrderUser.getProduct()))
                .quantity(itemOrderUser.getQuantity())
                .total(itemOrderUser.getTotal()).build();
    }

    public static ItemOrderUser toEntity(ItemOrderUserDto itemOrderUserDto){
        if (itemOrderUserDto == null){
            return null;
        }
        ItemOrderUser itemOrderUser = new ItemOrderUser();
        itemOrderUser.setId(itemOrderUserDto.getId());
        itemOrderUser.setProduct(ProductDto.toEntity(itemOrderUserDto.getProductDto()));
        itemOrderUser.setQuantity(itemOrderUserDto.getQuantity());
        itemOrderUser.setTotal(itemOrderUserDto.getTotal());
        return itemOrderUser;
    }
}
