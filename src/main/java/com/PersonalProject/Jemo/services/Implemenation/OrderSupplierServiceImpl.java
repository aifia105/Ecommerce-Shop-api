package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.ItemOrderSupplierDto;
import com.PersonalProject.Jemo.dto.OrderSupplierDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import com.PersonalProject.Jemo.model.ItemOrderSupplier;
import com.PersonalProject.Jemo.model.OrderSupplier;
import com.PersonalProject.Jemo.model.Product;
import com.PersonalProject.Jemo.model.Supplier;
import com.PersonalProject.Jemo.repository.ItemOrderSupplierRepository;
import com.PersonalProject.Jemo.repository.OrderSupplierRepository;
import com.PersonalProject.Jemo.repository.ProductRepository;
import com.PersonalProject.Jemo.repository.SupplierRepository;
import com.PersonalProject.Jemo.services.OrderSupplierService;
import com.PersonalProject.Jemo.validator.OrderSupplierValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderSupplierServiceImpl implements OrderSupplierService {

    private OrderSupplierRepository orderSupplierRepository;
    private ItemOrderSupplierRepository itemOrderSupplierRepository;
    private SupplierRepository supplierRepository;
    private ProductRepository productRepository;

    public OrderSupplierServiceImpl(OrderSupplierRepository orderSupplierRepository,ItemOrderSupplierRepository itemOrderSupplierRepository
            , SupplierRepository supplierRepository,ProductRepository productRepository) {
        super();
        this.orderSupplierRepository = orderSupplierRepository;
        this.itemOrderSupplierRepository = itemOrderSupplierRepository;
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderSupplierDto save(OrderSupplierDto orderSupplierDto) {
        List<String> error = OrderSupplierValidator.validator(orderSupplierDto);
        if(!error.isEmpty()){
            log.error("order supplier invalid {}",orderSupplierDto);
            throw new EntityNotValidException("order supplier invalid", ErrorCodes.ORDER_SUPPLIER_NOT_VALID, error);
        }
        Optional<Supplier> supplier = supplierRepository.findById(orderSupplierDto.getSupplierDto().getId());
        if(supplier.isEmpty()){
            log.warn("Supplier not found in database");
            throw new EntityNotFoundException("Supplier not found in database with this id" + orderSupplierDto.getSupplierDto().getId() ,ErrorCodes.SUPPLIER_NOT_FOUND);
        }
        List<String> productError = new ArrayList<>();
        if (orderSupplierDto.getItemOrderSupplierDtos() != null){
            orderSupplierDto.getItemOrderSupplierDtos().forEach(item ->{
                if (item.getProductDto().getId() != null){
                    Optional<Product> product = productRepository.findById(item.getProductDto().getId());
                    if (product.isEmpty()){
                        productError.add("Product with id was not found " + item.getProductDto().getId());
                    }
                    else {
                        productError.add("Cant save a order with a Product id NULL ");
                    }
                }
            });
        }
        if (!productError.isEmpty()){
            log.warn("Product not found in the database");
            throw new EntityNotValidException("Product not found in the database",ErrorCodes.PRODUCT_NOT_FOUND,productError);
        }
        orderSupplierDto.setDateOrder(Instant.now());
        OrderSupplier savedOrderSupplier = orderSupplierRepository.save(OrderSupplierDto.toEntity(orderSupplierDto));

        if (orderSupplierDto.getItemOrderSupplierDtos() != null){
            orderSupplierDto.getItemOrderSupplierDtos().forEach(item ->{
                ItemOrderSupplier itemOrderSupplier = ItemOrderSupplierDto.toEntity(item);
                itemOrderSupplier.setOrderSupplier(savedOrderSupplier);
                itemOrderSupplierRepository.save(itemOrderSupplier);
            });
        }



        return OrderSupplierDto.fromEntity(savedOrderSupplier);
    }

    @Override
    public OrderSupplierDto findById(Long id) {
        if(id == null){
            log.error("Order supplier id is null");
            return null;
        }
        return orderSupplierRepository.findById(id)
                .map(OrderSupplierDto::fromEntity)
                .orElseThrow(
                        ()-> new EntityNotFoundException("Order supplier not found", ErrorCodes.ORDER_SUPPLIER_NOT_FOUND));
    }

    @Override
    public List<OrderSupplierDto> findAll() {
        return orderSupplierRepository.findAll().stream()
                .map(OrderSupplierDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            log.error("Order supplier id is null");
            return;
        }
        List<ItemOrderSupplier> itemOrderSuppliers = itemOrderSupplierRepository.findAllByOrderSupplierId(id);
        if (!itemOrderSuppliers.isEmpty()) {
            throw new OperationNotValidException("Can not delete a order in use ",ErrorCodes.ORDER_SUPPLIER_ALREADY_IN_USE);
        }
        orderSupplierRepository.deleteById(id);

    }
}
