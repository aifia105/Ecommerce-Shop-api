package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.ItemOrderCustomerDto;
import com.PersonalProject.Jemo.dto.ItemOrderSupplierDto;
import com.PersonalProject.Jemo.dto.ProductDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import com.PersonalProject.Jemo.model.ItemOrderCustomer;
import com.PersonalProject.Jemo.model.ItemOrderSupplier;
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
    private final ItemOrderCustomerRepository itemOrderCustomerRepository;
    private final ItemOrderSupplierRepository itemOrderSupplierRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ItemOrderCustomerRepository itemOrderCustomerRepository,ItemOrderSupplierRepository itemOrderSupplierRepository) {
        super();
        this.productRepository = productRepository;
        this.itemOrderCustomerRepository = itemOrderCustomerRepository;
        this.itemOrderSupplierRepository = itemOrderSupplierRepository;

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
    public List<ItemOrderCustomerDto> findHistoryOrderClient(Long id) {
        return itemOrderCustomerRepository.findAllByProductId(id).stream()
                .map(ItemOrderCustomerDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<ItemOrderSupplierDto> findHistoryOrderSupplier(Long id) {
        return itemOrderSupplierRepository.findAllByProductId(id).stream()
                .map(ItemOrderSupplierDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Product ID is Null");
            return;
        }
        List<ItemOrderCustomer> itemOrderCustomers = itemOrderCustomerRepository.findAllByProductId(id);
        if (!itemOrderCustomers.isEmpty()){
            throw new OperationNotValidException("Product already in use(Order)",ErrorCodes.PRODUCT_ALREADY_IN_USE);
        }
        List<ItemOrderSupplier> itemOrderSuppliers = itemOrderSupplierRepository.findAllByProductId(id);
        if (!itemOrderSuppliers.isEmpty()){
            throw new OperationNotValidException("Product already in use(Supplier)",ErrorCodes.PRODUCT_ALREADY_IN_USE);
        }
            productRepository.deleteById(id);

    }
}
