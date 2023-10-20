package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.ItemOrderUserDto;
import com.PersonalProject.Jemo.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto productDto);

    ProductDto findByName(String productName);

    ProductDto findById(Long id);

    List<ProductDto> findAll();

    List<ProductDto> findAllByCategoryId(Long idCategory);

    List<ItemOrderUserDto> findHistoryOrderUser(Long id);


    void delete(Long id);
}
