package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.OrderSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderSupplierRepository extends JpaRepository<OrderSupplier, Integer> {

    List<OrderSupplier> findAllBySupplierId(Integer id);
}