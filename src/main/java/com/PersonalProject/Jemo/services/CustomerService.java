package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.CustomerDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;

import java.util.List;

public interface CustomerService {

    CustomerDto save(CustomerDto customerDto);
    CustomerDto findById(Long id);
    CustomerDto findByEmail(String customerEmail);
    List<CustomerDto> findAll();
    void delete(Long id);
    CustomerDto changePassWord(ModifyPasswordDto modifyPasswordDto);


}
