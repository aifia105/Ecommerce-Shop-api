package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
