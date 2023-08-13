package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.CustomerDto;
import com.PersonalProject.Jemo.dto.ItemOrderCustomerDto;
import com.PersonalProject.Jemo.dto.OrderCustomerDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import com.PersonalProject.Jemo.model.*;
import com.PersonalProject.Jemo.repository.CustomerRepository;
import com.PersonalProject.Jemo.repository.ItemOrderCustomerRepository;
import com.PersonalProject.Jemo.repository.OrderCustomerRepository;
import com.PersonalProject.Jemo.repository.ProductRepository;
import com.PersonalProject.Jemo.services.OrderCustomerService;
import com.PersonalProject.Jemo.validator.OrderCustomerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderCustomerServiceImpl implements OrderCustomerService {

    private final OrderCustomerRepository orderCustomerRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ItemOrderCustomerRepository itemOrderCustomerRepository;
    public OrderCustomerServiceImpl(OrderCustomerRepository orderCustomerRepository, ItemOrderCustomerRepository itemOrderCustomerRepository
            ,CustomerRepository customerRepository,ProductRepository productRepository) {
        super();
        this.orderCustomerRepository = orderCustomerRepository;
        this.itemOrderCustomerRepository = itemOrderCustomerRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderCustomerDto save(OrderCustomerDto orderCustomerDto) {
        List<String> errors = OrderCustomerValidator.validator(orderCustomerDto);
        if(!errors.isEmpty()){
            log.error("order invalid {}",orderCustomerDto);
            throw new EntityNotValidException("order invalid", ErrorCodes.ORDER_CUSTOMER_NOT_VALID, errors);
        }
        if (orderCustomerDto.getId() != null && orderCustomerDto.isOrderDELIVERED()){
            throw new OperationNotValidException("Cant update DELIVERED order",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }

        Optional<Customer> customer = customerRepository.findById(orderCustomerDto.getCustomerDto().getId());
        if(customer.isEmpty()){
            log.warn("Customer not found in BD");
            throw  new EntityNotFoundException("no user in database with this id" + orderCustomerDto.getCustomerDto().getId()
                    ,ErrorCodes.USER_NOT_FOUND);
        }
        List<String> productsErrors = new ArrayList<>();

        if(orderCustomerDto.getItemOrderCustomerDtos() != null){
            orderCustomerDto.getItemOrderCustomerDtos().forEach(itemOder->{
                if (itemOder.getProductDto() != null){
                    Optional<Product> product = productRepository.findById(itemOder.getProductDto().getId());
                    if (product.isEmpty()){
                        productsErrors.add("Product with this id was not found " + itemOder.getProductDto().getId());
                    }
                } else {
                    productsErrors.add("Cant save a order with a Product id NULL ");
                }
            });
        }
        if (!productsErrors.isEmpty()){
            log.warn("Product not found in the database");
            throw new EntityNotValidException("Product not found in the database",ErrorCodes.PRODUCT_NOT_FOUND,productsErrors);
        }
        orderCustomerDto.setDateOrder(Instant.now());
        OrderCustomer savedOrderCustomer = orderCustomerRepository.save(OrderCustomerDto.toEntity(orderCustomerDto));

        if(orderCustomerDto.getItemOrderCustomerDtos() != null){
        orderCustomerDto.getItemOrderCustomerDtos().forEach(item -> {
            ItemOrderCustomer itemOrderCustomer = ItemOrderCustomerDto.toEntity(item);
            itemOrderCustomer.setOrderCustomer(savedOrderCustomer);
            itemOrderCustomerRepository.save(itemOrderCustomer);
        });}


        return OrderCustomerDto.fromEntity(savedOrderCustomer);
    }

    @Override
    public OrderCustomerDto findById(Long id) {
        if(id == null){
            log.error("order id is null");
            return null;
        }
        return orderCustomerRepository.findById(id)
                .map(OrderCustomerDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("order not found" + id, ErrorCodes.ORDER_CUSTOMER_NOT_FOUND));
    }

    @Override
    public List<OrderCustomerDto> findAll() {
        return orderCustomerRepository.findAll().stream()
                .map(OrderCustomerDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            log.error("order id is null");
            return;
        }
        List<ItemOrderCustomer> itemOrderCustomers = itemOrderCustomerRepository.findAllByOrderCustomerId(id);
        if (!itemOrderCustomers.isEmpty()){
            throw new OperationNotValidException("Can not delete a order in use ",ErrorCodes.ORDER_CUSTOMER_ALREADY_IN_USE);
        }
            orderCustomerRepository.deleteById(id);


    }
    @Override
    public List<ItemOrderCustomerDto> findAllByOrderId(Long id) {
        return itemOrderCustomerRepository.findAllByOrderCustomerId(id).stream()
                .map(ItemOrderCustomerDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public OrderCustomerDto updateOrderStatus(Long id, OrderStatu orderStatu) {
        if (id == null){
            log.error("order id is null");
            throw new OperationNotValidException("Cant update order status ID is NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        if (!StringUtils.hasLength(String.valueOf(orderStatu))){
            log.error("order status is null");
            throw new OperationNotValidException("Cant update order status to NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        OrderCustomerDto orderCustomerDto = findById(id);
        if(orderCustomerDto.isOrderDELIVERED()){
            throw new OperationNotValidException("Cant update DELIVERED order",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        orderCustomerDto.setOrderStatu(orderStatu);

        return OrderCustomerDto.fromEntity(
                orderCustomerRepository.save(OrderCustomerDto.toEntity(orderCustomerDto))
        );
    }

    @Override
    public OrderCustomerDto updateQuantityOrder(Long id, Long idItem, BigDecimal quantity) {
        if (id == null){
            log.error("order id is null");
            throw new OperationNotValidException("Cant update order  ID is NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        if (idItem == null){
            log.error("order id is null");
            throw new OperationNotValidException("Cant update order  ID item order is NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0){
            log.error("quantity id is null");
            throw new OperationNotValidException("Cant update order quantity is 0 or NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        OrderCustomerDto orderCustomerDto = findById(id);
        if(orderCustomerDto.isOrderDELIVERED()){
            throw new OperationNotValidException("Cant update DELIVERED order",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        Optional<ItemOrderCustomer> itemOrderCustomerOptional = itemOrderCustomerRepository.findById(idItem);
        if (itemOrderCustomerOptional.isEmpty()){
            throw new EntityNotFoundException("No items with this ID" + idItem , ErrorCodes.ORDER_CUSTOMER_NOT_FOUND);
        }
        ItemOrderCustomer itemOrderCustomer = itemOrderCustomerOptional.get();
        itemOrderCustomer.setQuantity(quantity);
        itemOrderCustomerRepository.save(itemOrderCustomer);

        return OrderCustomerDto.fromEntity(
                orderCustomerRepository.save(OrderCustomerDto.toEntity(orderCustomerDto))
        );
    }

    @Override
    public OrderCustomerDto updateCustomer(Long id, Long idCustomer) {
        if (id == null){
            log.error("order id is null");
            throw new OperationNotValidException("Cant update order  ID is NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        if (idCustomer == null){
            log.error("order id is null");
            throw new OperationNotValidException("Cant update order  ID customer is NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        OrderCustomerDto orderCustomerDto = findById(id);
        if(orderCustomerDto.isOrderDELIVERED()){
            throw new OperationNotValidException("Cant update DELIVERED order",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        Optional<Customer> customerOptional = customerRepository.findById(idCustomer);
        if(customerOptional.isEmpty()){
            throw new EntityNotFoundException("No customers with Id" + idCustomer,ErrorCodes.CUSTOMER_NOT_FOUND);
        }
        orderCustomerDto.setCustomerDto(CustomerDto.fromEntity(customerOptional.get()));

        return OrderCustomerDto.fromEntity(
                orderCustomerRepository.save(OrderCustomerDto.toEntity(orderCustomerDto))
        );
    }
}
