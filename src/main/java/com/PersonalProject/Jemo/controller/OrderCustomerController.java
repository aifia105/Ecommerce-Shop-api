package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.OrderCustomerApi;
import com.PersonalProject.Jemo.dto.ItemOrderCustomerDto;
import com.PersonalProject.Jemo.dto.OrderCustomerDto;
import com.PersonalProject.Jemo.model.OrderStatu;
import com.PersonalProject.Jemo.services.OrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class OrderCustomerController implements OrderCustomerApi {

    private final OrderCustomerService orderCustomerService;

    @Autowired
    public OrderCustomerController(OrderCustomerService orderCustomerService) {
        super();
        this.orderCustomerService = orderCustomerService;
    }

    @Override
    public ResponseEntity<OrderCustomerDto> save(OrderCustomerDto orderCustomerDto) {
        return ResponseEntity.ok(orderCustomerService.save(orderCustomerDto));
    }

    @Override
    public ResponseEntity<OrderCustomerDto> findById(Long id) {
        return ResponseEntity.ok(orderCustomerService.findById(id));
    }

    @Override
    public ResponseEntity<List<OrderCustomerDto>> findAll() {
        return ResponseEntity.ok(orderCustomerService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
         orderCustomerService.delete(id);
         return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<ItemOrderCustomerDto>> findAllByOrderId(Long id) {
        return ResponseEntity.ok(orderCustomerService.findAllByOrderId(id));
    }
    @Override
    public ResponseEntity<OrderCustomerDto> updateOrderStatus(Long id, OrderStatu orderStatu) {
        return ResponseEntity.ok(orderCustomerService.updateOrderStatus(id,orderStatu));
    }

    @Override
    public ResponseEntity<OrderCustomerDto> updateQuantityOrder(Long id, Long idItem, BigDecimal quantity) {
        return ResponseEntity.ok(orderCustomerService.updateQuantityOrder(id,idItem,quantity));
    }

    @Override
    public ResponseEntity<OrderCustomerDto> updateCustomer(Long id, Long idCustomer) {
        return ResponseEntity.ok(orderCustomerService.updateCustomer(id,idCustomer));
    }

    @Override
    public ResponseEntity<OrderCustomerDto> updateProduct(Long id, Long idItem, Long idProduct) {
        return ResponseEntity.ok(orderCustomerService.updateProduct(id,idItem,idProduct));
    }

    @Override
    public ResponseEntity<OrderCustomerDto> deleteProduct(Long id, Long idItem) {
        return ResponseEntity.ok(orderCustomerService.deleteProduct(id,idItem));
    }
}
