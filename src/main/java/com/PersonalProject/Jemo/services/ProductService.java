package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.ItemOrderCustomerDto;
import com.PersonalProject.Jemo.dto.ItemOrderSupplierDto;
import com.PersonalProject.Jemo.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto productDto);

    ProductDto findByName(String productName);

    ProductDto findById(Long id);

    List<ProductDto> findAll();

    List<ProductDto> findAllByCategoryId(Long idCategory);

    List<ItemOrderCustomerDto> findHistoryOrderClient(Long id);

    List<ItemOrderSupplierDto> findHistoryOrderSupplier(Long id);

    void delete(Long id);
}
