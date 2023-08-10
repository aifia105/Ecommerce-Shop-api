package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.ProductApi;
import com.PersonalProject.Jemo.dto.ItemOrderCustomerDto;
import com.PersonalProject.Jemo.dto.ItemOrderSupplierDto;
import com.PersonalProject.Jemo.dto.ProductDto;
import com.PersonalProject.Jemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductApi {

    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        return productService.save(productDto);
    }

    @Override
    public ProductDto findByName(String productName) {
        return productService.findByName(productName);
    }

    @Override
    public ProductDto findById(Long id) {
        return productService.findById(id);
    }

    @Override
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @Override
    public List<ProductDto> findAllByCategoryId(Long idCategory) {
        return productService.findAllByCategoryId(idCategory);
    }

    @Override
    public List<ItemOrderCustomerDto> findHistoryOrderClient(Long id) {
        return productService.findHistoryOrderClient(id);
    }

    @Override
    public List<ItemOrderSupplierDto> findHistoryOrderSupplier(Long id) {
        return productService.findHistoryOrderSupplier(id);
    }

    @Override
    public void delete(Long id) {
    productService.delete(id);
    }
}
