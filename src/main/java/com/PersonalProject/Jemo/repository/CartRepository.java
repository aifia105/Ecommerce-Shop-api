package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart , Long> {
    Optional<Cart> findByUserId(Long id);
    List<Cart> findAllByUserId(Long id);
}
