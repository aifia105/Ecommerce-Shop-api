package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.ItemOrderSupplier;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemOrderSupplierDto {

    private Long id;

    private ProductDto productDto;

    private Integer quantity;

    private Integer unit_price;

    private OrderSupplierDto orderSupplierDto;

    public static ItemOrderSupplierDto fromEntity(ItemOrderSupplier itemOrderSupplier){
        if (itemOrderSupplier == null){
            return null;
        }
        return ItemOrderSupplierDto.builder()
                .id(itemOrderSupplier.getId())
                .productDto(ProductDto.formEntity(itemOrderSupplier.getProduct()))
                .quantity(itemOrderSupplier.getQuantity())
                .unit_price(itemOrderSupplier.getUnit_price()).build();
    }

    public static ItemOrderSupplier toEntity(ItemOrderSupplierDto itemOrderSupplierDto){
        if (itemOrderSupplierDto == null){
            return null;
        }
        ItemOrderSupplier itemOrderSupplier = new ItemOrderSupplier();
        itemOrderSupplier.setId(itemOrderSupplierDto.getId());
        itemOrderSupplier.setProduct(ProductDto.toEntity(itemOrderSupplierDto.getProductDto()));
        itemOrderSupplier.setQuantity(itemOrderSupplierDto.getQuantity());
        itemOrderSupplier.setUnit_price(itemOrderSupplierDto.getUnit_price());
        return itemOrderSupplier;
    }
}