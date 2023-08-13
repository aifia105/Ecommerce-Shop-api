package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.CustomerApi;
import com.PersonalProject.Jemo.dto.CustomerDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import com.PersonalProject.Jemo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<CustomerDto> save(CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.save(customerDto));
    }

    @Override
    public ResponseEntity<CustomerDto> findById(Long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @Override
    public ResponseEntity<CustomerDto> findByEmail(String customerEmail) {
        return ResponseEntity.ok(customerService.findByEmail(customerEmail));
    }

    @Override
    public ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
         customerService.delete(id);
         return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CustomerDto> changePassWord(ModifyPasswordDto modifyPasswordDto) {
        return ResponseEntity.ok(customerService.changePassWord(modifyPasswordDto));
    }
}
