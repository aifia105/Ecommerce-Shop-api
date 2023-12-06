package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.ItemOrderUserDto;
import com.PersonalProject.Jemo.dto.OrderUserDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderUserService {

    OrderUserDto save(OrderUserDto orderUserDto);
    OrderUserDto findById(Long id);
    List<OrderUserDto> findAll();
    void delete(Long  id);
    List<OrderUserDto> findAllByOrderId(Long id);
    OrderUserDto updateOrderStatus(Long id, String  orderStatus);
    OrderUserDto updateQuantityOrder(Long  id, Long  idItem, BigDecimal quantity);
    OrderUserDto updateUser(Long id, Long  idCustomer);
    OrderUserDto updateProduct(Long  id,Long  idItem , Long  idProduct);
    OrderUserDto deleteProduct(Long  id, Long  idItem);


}
