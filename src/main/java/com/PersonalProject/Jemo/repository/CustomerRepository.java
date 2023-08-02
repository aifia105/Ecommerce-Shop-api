package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findCustomerByEmail(String customerEmail);
}
