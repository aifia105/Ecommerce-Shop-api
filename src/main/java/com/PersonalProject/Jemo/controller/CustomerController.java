package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.CustomerApi;
import com.PersonalProject.Jemo.dto.CustomerDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import com.PersonalProject.Jemo.services.CustomerService;

import java.util.List;

public class CustomerController implements CustomerApi {

    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        return customerService.save(customerDto);
    }

    @Override
    public CustomerDto findById(Long id) {
        return customerService.findById(id);
    }

    @Override
    public CustomerDto findByEmail(String customerEmail) {
        return customerService.findByEmail(customerEmail);
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerService.findAll();
    }

    @Override
    public void delete(Long id) {
         customerService.delete(id);
    }

    @Override
    public CustomerDto changePassWord(ModifyPasswordDto modifyPasswordDto) {
        return customerService.changePassWord(modifyPasswordDto);
    }
}
