package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.OrderCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderCustomerRepository extends JpaRepository<OrderCustomer, Long> {

    List<OrderCustomer> findAllByCustomerId(Long id);


}
