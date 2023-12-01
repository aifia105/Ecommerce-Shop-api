package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.ItemOrderUserDto;
import com.PersonalProject.Jemo.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto productDto);

    ProductDto findByName(String productName);

    ProductDto findById(String id);

    List<ProductDto> findAll();

    List<ProductDto> findAllByCategoryId(String idCategory);

    List<ItemOrderUserDto> findHistoryOrderUser(String id);


    void delete(String id);
}
