package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.ItemOrderUserDto;
import com.PersonalProject.Jemo.dto.OrderUserDto;
import com.PersonalProject.Jemo.model.OrderStatu;

import java.math.BigDecimal;
import java.util.List;

public interface OrderUserService {

    OrderUserDto save(OrderUserDto orderUserDto);
    OrderUserDto findById(Long id);
    List<OrderUserDto> findAll();
    void delete(Long id);
    List<ItemOrderUserDto> findAllByOrderId(Long id);
    OrderUserDto updateOrderStatus(Long id, OrderStatu orderStatu);
    OrderUserDto updateQuantityOrder(Long id, Long idItem, BigDecimal quantity);
    OrderUserDto updateUser(Long id, Long idCustomer);
    OrderUserDto updateProduct(Long id, Long idItem , Long idProduct);
    OrderUserDto deleteProduct(Long id, Long idItem);


}
