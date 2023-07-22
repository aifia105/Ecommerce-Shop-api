package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto productDto);

    ProductDto findByName(String productName);

    ProductDto findById(Integer id);

    List<ProductDto> findAll();

    /*ProductDto update(ProductDto productDto,Integer id);*/

    List<ProductDto> findAllByCategoryId(Integer idCategory);

    void delete(Integer id);
}
