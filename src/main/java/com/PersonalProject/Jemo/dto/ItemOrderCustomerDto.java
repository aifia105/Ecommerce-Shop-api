package com.PersonalProject.Jemo.dto;

import com.PersonalProject.Jemo.model.ItemOrderCustomer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemOrderCustomerDto {

    private Integer id;

    private ProductDto productDto;

    private Integer quantity;

    private Integer unit_price;

    @JsonIgnore
    private OrderCustomerDto orderCustomerDto;

    public static ItemOrderCustomerDto fromEntity(ItemOrderCustomer itemOrderCustomer){
        if (itemOrderCustomer == null){
            return null;
        }
        return ItemOrderCustomerDto.builder()
                .id(itemOrderCustomer.getId())
                .productDto(ProductDto.formEntity(itemOrderCustomer.getProduct()))
                .quantity(itemOrderCustomer.getQuantity())
                .unit_price(itemOrderCustomer.getUnit_price()).build();
    }

    public static ItemOrderCustomer toEntity(ItemOrderCustomerDto itemOrderCustomerDto){
        if (itemOrderCustomerDto == null){
            return null;
        }
        ItemOrderCustomer itemOrderCustomer = new ItemOrderCustomer();
        itemOrderCustomer.setId(itemOrderCustomerDto.getId());
        itemOrderCustomer.setProduct(ProductDto.toEntity(itemOrderCustomerDto.getProductDto()));
        itemOrderCustomer.setQuantity(itemOrderCustomerDto.getQuantity());
        itemOrderCustomer.setUnit_price(itemOrderCustomerDto.getUnit_price());
        return itemOrderCustomer;
    }
}
