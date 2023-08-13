package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.OrderSupplierApi;
import com.PersonalProject.Jemo.dto.OrderSupplierDto;
import com.PersonalProject.Jemo.services.OrderSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
}
