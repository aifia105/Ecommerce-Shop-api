package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.OrderSupplierDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface OrderSupplierService {

    OrderSupplierDto save(OrderSupplierDto orderSupplierDto);
    OrderSupplierDto findById(Long id);
    List<OrderSupplierDto> findAll();
    void delete(Long id);

}
