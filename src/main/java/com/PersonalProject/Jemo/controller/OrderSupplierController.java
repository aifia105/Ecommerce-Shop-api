package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.OrderSupplierApi;
import com.PersonalProject.Jemo.dto.ItemOrderSupplierDto;
import com.PersonalProject.Jemo.dto.OrderSupplierDto;
import com.PersonalProject.Jemo.model.OrderStatu;
import com.PersonalProject.Jemo.services.OrderSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
@RestController
public class OrderSupplierController implements OrderSupplierApi {

    private final OrderSupplierService orderSupplierService;

    @Autowired
    public OrderSupplierController(OrderSupplierService orderSupplierService) {
        super();
        this.orderSupplierService = orderSupplierService;
    }

    @Override
    public ResponseEntity<OrderSupplierDto> save(OrderSupplierDto orderSupplierDto) {
        return ResponseEntity.ok(orderSupplierService.save(orderSupplierDto));
    }

    @Override
    public ResponseEntity<OrderSupplierDto> findById(Long id) {
        return ResponseEntity.ok(orderSupplierService.findById(id));
    }

    @Override
    public ResponseEntity<List<OrderSupplierDto>> findAll() {
        return ResponseEntity.ok(orderSupplierService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        orderSupplierService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<ItemOrderSupplierDto>> findAllByOrderId(Long id) {
        return ResponseEntity.ok(orderSupplierService.findAllByOrderId(id));
    }

    @Override
    public ResponseEntity<OrderSupplierDto> updateOrderStatus(Long id, OrderStatu orderStatu) {
        return ResponseEntity.ok(orderSupplierService.updateOrderStatus(id,orderStatu));
    }

    @Override
    public ResponseEntity<OrderSupplierDto> updateQuantityOrder(Long id, Long idItem, BigDecimal quantity) {
        return ResponseEntity.ok(orderSupplierService.updateQuantityOrder(id,idItem,quantity));
    }

    @Override
    public ResponseEntity<OrderSupplierDto> updateCustomer(Long id, Long idSupplier) {
        return ResponseEntity.ok(orderSupplierService.updateSupplier(id,idSupplier));
    }

    @Override
    public ResponseEntity<OrderSupplierDto> updateProduct(Long id, Long idItem, Long idProduct) {
        return ResponseEntity.ok(orderSupplierService.updateProduct(id,idItem,idProduct));
    }

    @Override
    public ResponseEntity<OrderSupplierDto> deleteProduct(Long id, Long idItem) {
        return ResponseEntity.ok(orderSupplierService.deleteProduct(id,idItem));
    }
}
