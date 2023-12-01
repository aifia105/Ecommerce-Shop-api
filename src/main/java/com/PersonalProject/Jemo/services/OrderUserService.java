package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.ItemOrderUserDto;
import com.PersonalProject.Jemo.dto.OrderUserDto;
import com.PersonalProject.Jemo.model.OrderStatu;

import java.math.BigDecimal;
import java.util.List;

public interface OrderUserService {

    OrderUserDto save(OrderUserDto orderUserDto);
    OrderUserDto findById(String id);
    List<OrderUserDto> findAll();
    void delete(String  id);
    List<ItemOrderUserDto> findAllByOrderId(String id);
    OrderUserDto updateOrderStatus(String  id, String  orderStatus);
    OrderUserDto updateQuantityOrder(String  id, String  idItem, BigDecimal quantity);
    OrderUserDto updateUser(String  id, String  idCustomer);
    OrderUserDto updateProduct(String  id, String  idItem , String  idProduct);
    OrderUserDto deleteProduct(String  id, String  idItem);


}
