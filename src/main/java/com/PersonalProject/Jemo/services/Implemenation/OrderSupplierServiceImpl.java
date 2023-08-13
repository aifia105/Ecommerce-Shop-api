package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.*;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import com.PersonalProject.Jemo.model.*;
import com.PersonalProject.Jemo.repository.ItemOrderSupplierRepository;
import com.PersonalProject.Jemo.repository.OrderSupplierRepository;
import com.PersonalProject.Jemo.repository.ProductRepository;
import com.PersonalProject.Jemo.repository.SupplierRepository;
import com.PersonalProject.Jemo.services.OrderSupplierService;
import com.PersonalProject.Jemo.validator.OrderSupplierValidator;
import com.PersonalProject.Jemo.validator.ProductValidator;
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
public class OrderSupplierServiceImpl implements OrderSupplierService {

    private final OrderSupplierRepository orderSupplierRepository;
    private final ItemOrderSupplierRepository itemOrderSupplierRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

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
        if (orderSupplierDto.getId() != null && orderSupplierDto.isOrderDELIVERED()){
            throw new OperationNotValidException("Cant update DELIVERED order",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        Optional<Supplier> supplier = supplierRepository.findById(orderSupplierDto.getSupplierDto().getId());
        if(supplier.isEmpty()){
            log.warn("Supplier not found in database");
            throw new EntityNotFoundException("Supplier not found in database with this id" + orderSupplierDto.getSupplierDto().getId() ,ErrorCodes.SUPPLIER_NOT_FOUND);
        }
        List<String> productError = new ArrayList<>();
        if (orderSupplierDto.getItemOrderSupplierDto() != null){
            orderSupplierDto.getItemOrderSupplierDto().forEach(item ->{
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

        if (orderSupplierDto.getItemOrderSupplierDto() != null){
            orderSupplierDto.getItemOrderSupplierDto().forEach(item ->{
                ItemOrderSupplier itemOrderSupplier = ItemOrderSupplierDto.toEntity(item);
                itemOrderSupplier.setOrderSupplier(savedOrderSupplier);
                ItemOrderSupplier savedItemOrder = itemOrderSupplierRepository.save(itemOrderSupplier);

                performInput(savedItemOrder);
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

    @Override
    public List<ItemOrderSupplierDto> findAllByOrderId(Long id) {
        return itemOrderSupplierRepository.findAllByOrderSupplierId(id).stream()
                .map(ItemOrderSupplierDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public OrderSupplierDto updateOrderStatus(Long id, OrderStatu orderStatu) {
        checkIdOrder(id);
        if (!StringUtils.hasLength(String.valueOf(orderStatu))){
            log.error("order status is null");
            throw new OperationNotValidException("Cant update order status to NULL",ErrorCodes.ORDER_SUPPLIER_NON_MODIFIABLE);
        }

        OrderSupplierDto orderSupplierDto = checkOrderStatus(id);
        orderSupplierDto.setOrderStatu(orderStatu);

        OrderSupplier savedOrderSupplier = orderSupplierRepository.save(OrderSupplierDto.toEntity(orderSupplierDto));
        if (orderSupplierDto.isOrderDELIVERED()){
            updateMvtStk(id);
        }

        return OrderSupplierDto.fromEntity(savedOrderSupplier);
    }

    @Override
    public OrderSupplierDto updateQuantityOrder(Long id, Long idItem, BigDecimal quantity) {
        checkIdOrder(id);
        checkIdItemOrder(idItem);

        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0){
            log.error("quantity id is null");
            throw new OperationNotValidException("Cant update order quantity is 0 or NULL",ErrorCodes.ORDER_SUPPLIER_NON_MODIFIABLE);
        }

        OrderSupplierDto orderSupplierDto = checkOrderStatus(id);
        Optional<ItemOrderSupplier> itemOrderSupplierOptional = findItemsOrder(idItem);
        if (itemOrderSupplierOptional.isPresent()){
            ItemOrderSupplier itemOrderSupplier = itemOrderSupplierOptional.get();
            itemOrderSupplier.setQuantity(quantity);
            itemOrderSupplierRepository.save(itemOrderSupplier);
        }

        return orderSupplierDto;
    }

    @Override
    public OrderSupplierDto updateSupplier(Long id, Long idSupplier) {
        checkIdOrder(id);
        if (idSupplier == null){
            log.error("order id is null");
            throw new OperationNotValidException("Cant update order  ID customer is NULL",ErrorCodes.ORDER_SUPPLIER_NON_MODIFIABLE);
        }
        OrderSupplierDto orderSupplierDto = checkOrderStatus(id);
        Optional<Supplier> SupplierOptional = supplierRepository.findById(idSupplier);
        if(SupplierOptional.isEmpty()){
            throw new EntityNotFoundException("No customers with Id" + idSupplier,ErrorCodes.CUSTOMER_NOT_FOUND);
        }
        orderSupplierDto.setSupplierDto(SupplierDto.fromEntity(SupplierOptional.get()));

        return OrderSupplierDto.fromEntity(
                orderSupplierRepository.save(OrderSupplierDto.toEntity(orderSupplierDto))
        );
    }

    @Override
    public OrderSupplierDto updateProduct(Long id, Long idItem, Long idProduct) {
        checkIdOrder(id);
        checkIdItemOrder(id);
        checkIDProduct(id);

        OrderSupplierDto orderSupplierDto = checkOrderStatus(id);
        Optional<Product> product = findProduct(idProduct);

        Optional<ItemOrderSupplier> itemOrderSupplierOptional = findItemsOrder(id);

        if (product.isEmpty()){
            throw new EntityNotFoundException("No products with this ID" + idProduct ,ErrorCodes.PRODUCT_NOT_FOUND);
        }
        List<String> errors = ProductValidator.validator(ProductDto.formEntity(product.get()));
        if (!errors.isEmpty()){
            throw new EntityNotValidException("Product invalid ",ErrorCodes.PRODUCT_NOT_VALID,errors);
        }
        if (itemOrderSupplierOptional.isPresent()) {
            ItemOrderSupplier itemOrderSupplier = itemOrderSupplierOptional.get();
            itemOrderSupplier.setProduct(product.get());
            itemOrderSupplierRepository.save(itemOrderSupplier);
        }
        return orderSupplierDto;
    }

    @Override
    public OrderSupplierDto deleteProduct(Long id, Long idItem) {
        return null;
    }

    private OrderSupplierDto checkOrderStatus(Long id){
        OrderSupplierDto orderSupplierDto = findById(id);
        if (orderSupplierDto.isOrderDELIVERED()){
            throw new OperationNotValidException("Cant update DELIVERED order",ErrorCodes.ORDER_CUSTOMER_NON_MODIFIABLE);
        }
        return orderSupplierDto;
    }
    private Optional<ItemOrderSupplier> findItemsOrder(Long idItem){
        Optional<ItemOrderSupplier> itemOrderSupplierOptional = itemOrderSupplierRepository.findById(idItem);
        if (itemOrderSupplierOptional.isEmpty()){
            throw new EntityNotFoundException("No items with this ID" + idItem , ErrorCodes.ORDER_SUPPLIER_NOT_FOUND);
        }
        return itemOrderSupplierOptional ;
    }
    private Optional<Product> findProduct(Long idProduct){
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isEmpty()){
            throw new EntityNotFoundException("No items with this ID" + idProduct , ErrorCodes.ORDER_CUSTOMER_NOT_FOUND);
        }
        return product ;
    }

    private void checkIdOrder(Long id){
        if (id == null){
            log.error("order id is null");
            throw new OperationNotValidException("Cant update order  ID is NULL",ErrorCodes.ORDER_SUPPLIER_NON_MODIFIABLE);
        }
    }
    private void checkIdItemOrder(Long idItem){
        if (idItem == null){
            log.error("order id is null");
            throw new OperationNotValidException("Cant update order  ID item is NULL",ErrorCodes.ORDER_SUPPLIER_NON_MODIFIABLE);
        }
    }
    private void checkIDProduct(Long idProduct){
        if (idProduct == null){
            log.error("product id is null");
            throw new OperationNotValidException("Cant update order  ID product is NULL",ErrorCodes.ORDER_SUPPLIER_NON_MODIFIABLE);
        }
    }
    private void updateMvtStk(Long id){
        List<ItemOrderSupplier> itemOrderSuppliers = itemOrderSupplierRepository.findAllByOrderSupplierId(id);
        itemOrderSuppliers.forEach(this::performInput);
    }
    private void performInput(ItemOrderSupplier itemOrderSupplier){
        MvtStkDto mvtStkDto = MvtStkDto.builder()
                .productDto(ProductDto.formEntity(itemOrderSupplier.getProduct()))
                .dateMvt(Instant.now())
                .typeMvt(TypeMvtStk.ENTRY)
                .quantity(itemOrderSupplier.getQuantity())
                .sourceMvtStk(SourceMvtStk.Order_Supplier)
                .build();
    }
}
