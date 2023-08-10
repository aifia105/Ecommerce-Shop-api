package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.ItemOrderCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOrderCustomerRepository extends JpaRepository<ItemOrderCustomer, Long> {

    List<ItemOrderCustomer> findAllByProductId(Long id);

    List<ItemOrderCustomer> findAllByOrderCustomerId(Long id);

}
