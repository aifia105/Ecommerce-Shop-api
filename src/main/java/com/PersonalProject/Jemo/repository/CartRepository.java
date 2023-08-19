package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.dto.CartDto;
import com.PersonalProject.Jemo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart , Long> {
    Optional<CartDto> findByCustomerId(Long id);
}
