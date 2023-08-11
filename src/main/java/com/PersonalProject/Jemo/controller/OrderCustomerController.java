package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.OrderCustomerApi;
import com.PersonalProject.Jemo.dto.OrderCustomerDto;
import com.PersonalProject.Jemo.services.OrderCustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderCustomerController implements OrderCustomerApi {

    private OrderCustomerService orderCustomerService;
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
}
