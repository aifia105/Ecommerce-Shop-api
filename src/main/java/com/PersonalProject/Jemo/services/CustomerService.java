package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.CustomerDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;

import java.util.List;

public interface CustomerService {

    CustomerDto save(CustomerDto customerDto);
    CustomerDto findById(Integer id);
    CustomerDto findByEmail(String customerEmail);
    List<CustomerDto> findAll();
    void delete(Integer id);
    CustomerDto changePassWord(ModifyPasswordDto modifyPasswordDto);


}
