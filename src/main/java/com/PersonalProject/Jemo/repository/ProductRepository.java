package com.PersonalProject.Jemo.repository;
import com.PersonalProject.Jemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
    List<Product> findAllByCategoryId(Long id);
}
