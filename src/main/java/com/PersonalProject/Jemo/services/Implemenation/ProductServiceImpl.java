package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.ProductDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.repository.ProductRepository;
import com.PersonalProject.Jemo.services.ProductService;
import com.PersonalProject.Jemo.validator.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private  ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;

    }

    @Override
    public ProductDto save(ProductDto productDto) {
        List<String> errors = ProductValidator.validator(productDto);
        if(!errors.isEmpty()){
            log.error("product invalid {}",productDto);
            throw new EntityNotValidException("product invalid", ErrorCodes.PRODUCT_NOT_VALID, errors);
        }
        return ProductDto.formEntity(
                productRepository.save(
                        ProductDto.toEntity(productDto)));
    }

    @Override
    public ProductDto findByName(String productName) {
        if(!StringUtils.hasLength(productName)){
            log.error("Product Name is Null");
            return null;
        }

        return productRepository.findByName(productName)
                .map(ProductDto::formEntity)
                .orElseThrow(() ->
                    new EntityNotFoundException(
                            "Product with the name" + productName + "not found",
                            ErrorCodes.PRODUCT_NOT_FOUND)
                );
    }

    @Override
    public ProductDto findById(Integer id) {
        if (id == null){
            log.error("Product ID is Null");
            return null;
        }
        return productRepository.findById(id)
                .map(ProductDto::formEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                        "Product with the ID" + id + "not found",
                        ErrorCodes.PRODUCT_NOT_FOUND));
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDto::formEntity)
                .collect(Collectors.toList());
    }

    /*@Override
    public ProductDto update(ProductDto productDto, Integer id) {
        List<String> errors = ProductValidator.validator(productDto);
        if(!errors.isEmpty()){
            log.error("Product not valid");
            throw new EntityNotValidException("product invalid", ErrorCodes.PRODUCT_NOT_VALID, errors);
        }
        if (id == null){
            log.error("Product ID is Null");
             return null;
        }

        return productRepository.s;
    }*/

    @Override
    public List<ProductDto> findAllByCategoryId(Integer idCategory) {
        if (idCategory == null){
            log.error("Category ID is Null");
            return null;
        }
        return productRepository.findAllByCategoryId(idCategory).stream()
                .map(ProductDto::formEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Product ID is Null");
        } else {
            productRepository.deleteById(id);
        }
    }
}
