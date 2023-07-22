package com.PersonalProject.Jemo.repository;
import com.PersonalProject.Jemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String productName);
    List<Product> findAllByCategoryId(Integer id);
}
