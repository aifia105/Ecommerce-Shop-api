package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart , String> {
    Optional<Cart> findByUserId(String id);
    List<Cart> findAllByUserId(String id);
}
