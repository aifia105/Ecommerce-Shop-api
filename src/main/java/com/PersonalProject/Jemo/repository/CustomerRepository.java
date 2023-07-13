package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
