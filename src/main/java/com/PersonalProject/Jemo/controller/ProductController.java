package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.ProductApi;
import com.PersonalProject.Jemo.dto.ItemOrderCustomerDto;
import com.PersonalProject.Jemo.dto.ItemOrderSupplierDto;
import com.PersonalProject.Jemo.dto.ProductDto;
import com.PersonalProject.Jemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductApi {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @Override
    public ResponseEntity<ProductDto> save(ProductDto productDto) {
        return ResponseEntity.ok(productService.save(productDto));
    }

    @Override
    public ResponseEntity<ProductDto> findByName(String productName) {
        return ResponseEntity.ok(productService.findByName(productName));
    }

    @Override
    public ResponseEntity<ProductDto> findById(Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @Override
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @Override
    public ResponseEntity<List<ProductDto>>  findAllByCategoryId(Long idCategory) {
        return ResponseEntity.ok(productService.findAllByCategoryId(idCategory));
    }

    @Override
    public ResponseEntity<List<ItemOrderCustomerDto>> findHistoryOrderClient(Long id) {
        return ResponseEntity.ok(productService.findHistoryOrderClient(id));
    }

    @Override
    public ResponseEntity<List<ItemOrderSupplierDto>> findHistoryOrderSupplier(Long id) {
        return ResponseEntity.ok(productService.findHistoryOrderSupplier(id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
    productService.delete(id);
    return ResponseEntity.ok().build();
    }
}
