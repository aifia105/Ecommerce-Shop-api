package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.ItemOrderCustomer;
import com.PersonalProject.Jemo.model.ItemOrderSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOrderSupplierRepository extends JpaRepository<ItemOrderSupplier, Long> {

    List<ItemOrderSupplier> findAllByProductId(Long id);

    List<ItemOrderSupplier> findAllByOrderSupplierId(Long id);
}
