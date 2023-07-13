package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.OrderCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCustomerRepository extends JpaRepository<OrderCustomer, Integer> {
}
