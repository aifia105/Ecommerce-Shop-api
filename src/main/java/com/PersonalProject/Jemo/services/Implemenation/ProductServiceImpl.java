package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.ItemOrderUserDto;
import com.PersonalProject.Jemo.dto.ProductDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import com.PersonalProject.Jemo.model.ItemOrderUser;
import com.PersonalProject.Jemo.repository.*;
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
    private final   ProductRepository productRepository;
    private final ItemOrderUserRepository itemOrderUserRepository;



    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ItemOrderUserRepository itemOrderUserRepository) {
        super();
        this.productRepository = productRepository;
        this.itemOrderUserRepository = itemOrderUserRepository;


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
    public ProductDto findById(Long id) {
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

    @Override
    public List<ProductDto> findAllByCategoryId(Long idCategory) {
        if (idCategory == null){
            log.error("Category ID is Null");
            return null;
        }
        return productRepository.findAllByCategoryId(idCategory).stream()
                .map(ProductDto::formEntity).collect(Collectors.toList());
    }

    @Override
    public List<ItemOrderUserDto> findHistoryOrderUser(Long id) {
        return itemOrderUserRepository.findAllByProductId(id).stream()
                .map(ItemOrderUserDto::fromEntity).collect(Collectors.toList());
    }



    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Product ID is Null");
            return;
        }
        List<ItemOrderUser> itemOrderUsers = itemOrderUserRepository.findAllByProductId(id);
        if (!itemOrderUsers.isEmpty()){
            throw new OperationNotValidException("Product already in use(Order)",ErrorCodes.PRODUCT_ALREADY_IN_USE);
        }


    }
}
