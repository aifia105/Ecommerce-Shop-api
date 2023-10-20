package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.OrderUserApi;
import com.PersonalProject.Jemo.dto.ItemOrderUserDto;
import com.PersonalProject.Jemo.dto.OrderUserDto;
import com.PersonalProject.Jemo.model.OrderStatu;
import com.PersonalProject.Jemo.services.OrderUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class OrderUserController implements OrderUserApi {

    private final OrderUserService orderUserService;

    @Autowired
    public OrderUserController(OrderUserService orderUserService) {
        super();
        this.orderUserService = orderUserService;
    }

    @Override
    public ResponseEntity<OrderUserDto> save(OrderUserDto orderUserDto) {
        return ResponseEntity.ok(orderUserService.save(orderUserDto));
    }

    @Override
    public ResponseEntity<OrderUserDto> findById(Long id) {
        return ResponseEntity.ok(orderUserService.findById(id));
    }

    @Override
    public ResponseEntity<List<OrderUserDto>> findAll() {
        return ResponseEntity.ok(orderUserService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
         orderUserService.delete(id);
         return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<ItemOrderUserDto>> findAllByOrderId(Long id) {
        return ResponseEntity.ok(orderUserService.findAllByOrderId(id));
    }
    @Override
    public ResponseEntity<OrderUserDto> updateOrderStatus(Long id, OrderStatu orderStatu) {
        return ResponseEntity.ok(orderUserService.updateOrderStatus(id,orderStatu));
    }

    @Override
    public ResponseEntity<OrderUserDto> updateQuantityOrder(Long id, Long idItem, BigDecimal quantity) {
        return ResponseEntity.ok(orderUserService.updateQuantityOrder(id,idItem,quantity));
    }

    @Override
    public ResponseEntity<OrderUserDto> updateUser(Long id, Long idCustomer) {
        return ResponseEntity.ok(orderUserService.updateUser(id,idCustomer));
    }

    @Override
    public ResponseEntity<OrderUserDto> updateProduct(Long id, Long idItem, Long idProduct) {
        return ResponseEntity.ok(orderUserService.updateProduct(id,idItem,idProduct));
    }

    @Override
    public ResponseEntity<OrderUserDto> deleteProduct(Long id, Long idItem) {
        return ResponseEntity.ok(orderUserService.deleteProduct(id,idItem));
    }
}
