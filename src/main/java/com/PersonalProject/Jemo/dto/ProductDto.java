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

    private String codeProduct;


    private String Name;


    private String description;


    private BigDecimal priceHT;


    private BigDecimal TVA;


    private BigDecimal priceTTC;


    private String picture;


    private CategoryDto category;


    @JsonIgnore
    private List<ItemOrderCustomerDto> itemOrderCustomerList;


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
                .codeProduct(product.getCodeProduct())
                .Name(product.getName())
                .description(product.getDescription())
                .priceHT(product.getPriceHT())
                .TVA(product.getTVA())
                .priceTTC(product.getPriceTTC())
                .picture(product.getPicture())
                .category(CategoryDto.fromEntity(product.getCategory())).build();
    }

    public static Product toEntity(ProductDto productDto){
        if (productDto == null){
            return null;
        }
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCodeProduct(productDto.getCodeProduct());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPriceHT(productDto.getPriceHT());
        product.setTVA(productDto.getTVA());
        product.setPriceTTC(productDto.getPriceTTC());
        product.setPicture(productDto.getPicture());
        product.setCategory(CategoryDto.toEntity(productDto.getCategory()));
        return product;
    }

}
