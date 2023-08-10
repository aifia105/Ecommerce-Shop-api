package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.OrderCustomerDto;

import java.util.List;

public interface OrderCustomerService {

    OrderCustomerDto save(OrderCustomerDto orderCustomerDto);
    OrderCustomerDto findById(Long id);
    List<OrderCustomerDto> findAll();
    void delete(Long id);
}
