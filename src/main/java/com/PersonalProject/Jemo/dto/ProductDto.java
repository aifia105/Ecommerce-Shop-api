package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductDto {

    private Long id;


    private String Name;


    private String description;


    private String brand;


    private BigDecimal priceTTC;


    private byte[] image;


    private CategoryDto category;


    @JsonIgnore
    private List<ItemOrderUserDto> itemOrderUserDtoList;


    @JsonIgnore
    private List<MvtStkDto> mvtStks;

    @JsonIgnore
    private List<RatingDto> rating;

    public static ProductDto formEntity(Product product){

        if (product == null){
            return null;
        }
        return ProductDto.builder()
                .id(product.getId())
                .Name(product.getName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .priceTTC(product.getPriceTTC())
                .image(product.getImage())
                .category(CategoryDto.fromEntity(product.getCategory())).build();
    }

    public static Product toEntity(ProductDto productDto){
        if (productDto == null){
            return null;
        }
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setBrand(productDto.getBrand());
        product.setPriceTTC(productDto.getPriceTTC());
        product.setImage(productDto.getImage());
        product.setCategory(CategoryDto.toEntity(productDto.getCategory()));
        return product;
    }

}
