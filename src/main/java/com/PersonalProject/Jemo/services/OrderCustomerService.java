package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.ItemOrderCustomerDto;
import com.PersonalProject.Jemo.dto.OrderCustomerDto;
import com.PersonalProject.Jemo.model.OrderStatu;

import java.math.BigDecimal;
import java.util.List;

public interface OrderCustomerService {

    OrderCustomerDto save(OrderCustomerDto orderCustomerDto);
    OrderCustomerDto findById(Long id);
    List<OrderCustomerDto> findAll();
    void delete(Long id);
    List<ItemOrderCustomerDto> findAllByOrderId(Long id);
    OrderCustomerDto updateOrderStatus(Long id, OrderStatu orderStatu);
    OrderCustomerDto updateQuantityOrder(Long id, Long idItem, BigDecimal quantity);
    OrderCustomerDto updateCustomer(Long id, Long idCustomer);

}
