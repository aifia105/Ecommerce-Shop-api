package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.*;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import com.PersonalProject.Jemo.model.*;
import com.PersonalProject.Jemo.repository.*;
import com.PersonalProject.Jemo.services.MvtStkService;
import com.PersonalProject.Jemo.services.OrderUserService;
import com.PersonalProject.Jemo.validator.OrderCustomerValidator;
import com.PersonalProject.Jemo.validator.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderUserServiceImpl implements OrderUserService {

    private final OrderUserRepository orderUserRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ItemOrderUserRepository itemOrderUserRepository;
    private final MvtStkService mvtStkService;

    @Autowired
    public OrderUserServiceImpl(OrderUserRepository orderUserRepository, ItemOrderUserRepository itemOrderUserRepository
            , UserRepository userRepository, ProductRepository productRepository, MvtStkService mvtStkService, CartRepository cartRepository) {
        super();
        this.orderUserRepository = orderUserRepository;
        this.itemOrderUserRepository = itemOrderUserRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.mvtStkService = mvtStkService;
        this.cartRepository = cartRepository;
    }

    @Override
    public OrderUserDto save(OrderUserDto orderUserDto) {

        List<String> errors = OrderCustomerValidator.validator(orderUserDto);

        if(!errors.isEmpty()){
            log.error("order invalid {}", orderUserDto);
            throw new EntityNotValidException("order invalid", ErrorCodes.ORDER_CUSTOMER_NOT_VALID, errors);
        }

        if (orderUserDto.getId() != null && orderUserDto.isOrderDELIVERED()){
            throw new OperationNotValidException("Cant update DELIVERED order",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }

        Optional<User> customer = userRepository.findById(orderUserDto.getUserDto().getId());
        if(customer.isEmpty()){
            log.warn("Customer not found in BD");
            throw  new EntityNotFoundException("no user in database with this id" + orderUserDto.getUserDto().getId()
                    ,ErrorCodes.USER_NOT_FOUND);
        }

        Optional<Cart> cart = cartRepository.findByUserId(orderUserDto.getUserDto().getId());
        if(cart.isEmpty()){
            log.warn("Cart is not added");
            throw  new EntityNotFoundException("no cart associate with this user id" + orderUserDto.getUserDto().getId()
                    ,ErrorCodes.CART_NOT_FOUND);
        }



        List<String> productsErrors = new ArrayList<>();

        if(orderUserDto.getItemOrderUserDtos() != null){
            orderUserDto.getItemOrderUserDtos().forEach(itemOder->{
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
        orderUserDto.setDateOrder(Instant.now());
        OrderUser savedOrderUser = orderUserRepository.save(OrderUserDto.toEntity(orderUserDto));
        System.out.println(orderUserDto);
        if(orderUserDto.getItemOrderUserDtos() != null){
        orderUserDto.getItemOrderUserDtos().forEach(item -> {
            ItemOrderUser itemOrderUser = ItemOrderUserDto.toEntity(item);
            itemOrderUser.setOrderUser(savedOrderUser);
            ItemOrderUser savedItemOrder = itemOrderUserRepository.save(itemOrderUser);

            performOutput(savedItemOrder);
            System.out.println(savedItemOrder);
        });}

        System.out.println(savedOrderUser);
        return OrderUserDto.fromEntity(savedOrderUser);
    }

    @Override
    public OrderUserDto findById(String  id) {
        if(id == null){
            log.error("order id is null");
            return null;
        }
        return orderUserRepository.findById(id)
                .map(OrderUserDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("order not found" + id, ErrorCodes.ORDER_CUSTOMER_NOT_FOUND));
    }

    @Override
    public List<OrderUserDto> findAll() {
        return orderUserRepository.findAll().stream()
                .map(OrderUserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String  id) {
        if(id == null){
            log.error("order id is null");
            return;
        }
        List<ItemOrderUser> itemOrderUsers = itemOrderUserRepository.findAllByOrderUserId(id);
        if (!itemOrderUsers.isEmpty()){
            throw new OperationNotValidException("Can not delete a order in use ",ErrorCodes.ORDER_CUSTOMER_ALREADY_IN_USE);
        }
            orderUserRepository.deleteById(id);


    }
    @Override
    public List<ItemOrderUserDto> findAllByOrderId(String  id) {
        return itemOrderUserRepository.findAllByOrderUserId(id).stream()
                .map(ItemOrderUserDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public OrderUserDto updateOrderStatus(String  id, String  orderStatus) {
        checkIdOrder(id);
        if (!StringUtils.hasLength(String.valueOf(orderStatus))){
            log.error("order status is null");
            throw new OperationNotValidException("Cant update order status to NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }

        OrderUserDto orderUserDto = checkOrderStatus(id);
        orderUserDto.setOrderStatus(orderStatus);

        OrderUser savedOrderUser = orderUserRepository.save(OrderUserDto.toEntity(orderUserDto));
        if (orderUserDto.isOrderDELIVERED()){
            updateMvtStk(id);
        }
        return OrderUserDto.fromEntity(savedOrderUser);
    }

    @Override
    public OrderUserDto updateQuantityOrder(String  id, String  idItem, BigDecimal quantity) {
        checkIdOrder(id);
        checkIdItemOrder(idItem);

        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0){
            log.error("quantity id is null");
            throw new OperationNotValidException("Cant update order quantity is 0 or NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }

        OrderUserDto orderUserDto = checkOrderStatus(id);
        Optional<ItemOrderUser> itemOrderCustomerOptional = findItemsOrder(idItem);
        if (itemOrderCustomerOptional.isPresent()){
            ItemOrderUser itemOrderUser = itemOrderCustomerOptional.get();
            itemOrderUser.setQuantity(quantity);
            itemOrderUserRepository.save(itemOrderUser);
        }

        return orderUserDto;
    }

    @Override
    public OrderUserDto updateUser(String  id, String  idCustomer) {
        checkIdOrder(id);
        if (idCustomer == null){
            log.error("order id is null");
            throw new OperationNotValidException("Cant update order  ID customer is NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        OrderUserDto orderUserDto = checkOrderStatus(id);
        Optional<User> customerOptional = userRepository.findById(idCustomer);
        if(customerOptional.isEmpty()){
            throw new EntityNotFoundException("No customers with Id" + idCustomer,ErrorCodes.USER_NOT_FOUND);
        }
        orderUserDto.setUserDto(UserDto.fromEntity(customerOptional.get()));

        return OrderUserDto.fromEntity(
                orderUserRepository.save(OrderUserDto.toEntity(orderUserDto))
        );
    }

    @Override
    public OrderUserDto updateProduct(String  id, String  idItem, String  idProduct) {
        checkIdOrder(id);
        checkIdItemOrder(id);
        checkIDProduct(id);

        OrderUserDto orderUserDto = checkOrderStatus(id);
        Optional<Product> product = findProduct(idProduct);

        Optional<ItemOrderUser> itemOrderCustomerOptional = findItemsOrder(id);

        if (product.isPresent()){
            List<String> errors = ProductValidator.validator(ProductDto.formEntity(product.get()));
            if (!errors.isEmpty()){
               throw new EntityNotValidException("Product invalid ",ErrorCodes.PRODUCT_NOT_VALID,errors);
            }
        }
        if (itemOrderCustomerOptional.isPresent()) {
            ItemOrderUser itemOrderUser = itemOrderCustomerOptional.get();
            product.ifPresent(itemOrderUser::setProduct);
            itemOrderUserRepository.save(itemOrderUser);
        }
        return orderUserDto;
    }

    @Override
    public OrderUserDto deleteProduct(String  id, String  idItem) {
        checkIdOrder(id);
        checkIdItemOrder(id);

        OrderUserDto orderUserDto = checkOrderStatus(id);
        findItemsOrder(id);
        itemOrderUserRepository.deleteById(idItem);
        return orderUserDto;
    }
    private OrderUserDto checkOrderStatus(String  id){
        OrderUserDto orderCustomer = findById(id);
        if (orderCustomer.isOrderDELIVERED()){
            throw new OperationNotValidException("Cant update DELIVERED order",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        return orderCustomer;
    }
    private Optional<ItemOrderUser> findItemsOrder(String  idItem){
        Optional<ItemOrderUser> itemOrderCustomerOptional = itemOrderUserRepository.findById(idItem);
        if (itemOrderCustomerOptional.isEmpty()){
            throw new EntityNotFoundException("No items with this ID" + idItem , ErrorCodes.ORDER_CUSTOMER_NOT_FOUND);
        }
        return itemOrderCustomerOptional ;
    }
    private Optional<Product> findProduct(String  idProduct){
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isEmpty()){
            throw new EntityNotFoundException("No items with this ID" + idProduct , ErrorCodes.ORDER_CUSTOMER_NOT_FOUND);
        }
        return product ;
    }

    private void checkIdOrder(String  id){
        if (!StringUtils.hasLength(id)){
            log.error("order id is null");
            throw new OperationNotValidException("Cant update order  ID is NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
    }
    private void checkIdItemOrder(String  idItem){
        if (!StringUtils.hasLength(idItem)){
            log.error("order id is null");
            throw new OperationNotValidException("Cant update order  ID item is NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
    }
    private void checkIDProduct(String  idProduct){
        if (!StringUtils.hasLength(idProduct)){
            log.error("product id is null");
            throw new OperationNotValidException("Cant update order  ID product is NULL",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
    }
    private void updateMvtStk(String  id){
        List<ItemOrderUser> itemOrderUsers = itemOrderUserRepository.findAllByOrderUserId(id);
        itemOrderUsers.forEach(this::performOutput);
    }
    private void performOutput(ItemOrderUser itemOrderUser){
        MvtStkDto mvtStkDto = MvtStkDto.builder()
                .productDto(ProductDto.formEntity(itemOrderUser.getProduct()))
                .dateMvt(Instant.now())
                .typeMvt(TypeMvtStk.EXIT)
                .quantity(itemOrderUser.getQuantity())
                .sourceMvtStk(SourceMvtStk.Order_Customer)
                .build();
        mvtStkService.exitStock(mvtStkDto);
    }
}
