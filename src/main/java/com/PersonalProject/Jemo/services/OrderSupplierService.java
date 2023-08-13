package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.ItemOrderSupplierDto;
import com.PersonalProject.Jemo.dto.OrderSupplierDto;
import com.PersonalProject.Jemo.model.OrderStatu;


import java.math.BigDecimal;
import java.util.List;

public interface OrderSupplierService {

    OrderSupplierDto save(OrderSupplierDto orderSupplierDto);
    OrderSupplierDto findById(Long id);
    List<OrderSupplierDto> findAll();
    void delete(Long id);
    List<ItemOrderSupplierDto> findAllByOrderId(Long id);
    OrderSupplierDto updateOrderStatus(Long id, OrderStatu orderStatu);
    OrderSupplierDto updateQuantityOrder(Long id, Long idItem, BigDecimal quantity);
    OrderSupplierDto updateSupplier(Long id, Long idSupplier);
    OrderSupplierDto updateProduct(Long id,Long idItem ,Long idProduct);
    OrderSupplierDto deleteProduct(Long id,Long idItem);

}
