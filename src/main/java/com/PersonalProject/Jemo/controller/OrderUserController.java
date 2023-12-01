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
    public ResponseEntity<OrderUserDto> findById(String id) {
        return ResponseEntity.ok(orderUserService.findById(id));
    }

    @Override
    public ResponseEntity<List<OrderUserDto>> findAll() {
        return ResponseEntity.ok(orderUserService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
         orderUserService.delete(id);
         return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<ItemOrderUserDto>> findAllByOrderId(String id) {
        return ResponseEntity.ok(orderUserService.findAllByOrderId(id));
    }
    @Override
    public ResponseEntity<OrderUserDto> updateOrderStatus(String id, String orderStatus) {
        return ResponseEntity.ok(orderUserService.updateOrderStatus(id,orderStatus));
    }

    @Override
    public ResponseEntity<OrderUserDto> updateQuantityOrder(String id, String idItem, BigDecimal quantity) {
        return ResponseEntity.ok(orderUserService.updateQuantityOrder(id,idItem,quantity));
    }

    @Override
    public ResponseEntity<OrderUserDto> updateUser(String id, String idCustomer) {
        return ResponseEntity.ok(orderUserService.updateUser(id,idCustomer));
    }

    @Override
    public ResponseEntity<OrderUserDto> updateProduct(String id, String idItem, String idProduct) {
        return ResponseEntity.ok(orderUserService.updateProduct(id,idItem,idProduct));
    }

    @Override
    public ResponseEntity<OrderUserDto> deleteProduct(String id, String idItem) {
        return ResponseEntity.ok(orderUserService.deleteProduct(id,idItem));
    }
}
