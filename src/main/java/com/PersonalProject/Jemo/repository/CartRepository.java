package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart , Long> {
    @Query("select DISTINCT c from Cart c where c.user.id = :id")
    Optional<Cart> findByUserId(Long id);
    @Query("select  c from Cart c where c.user.id = :id")
    List<Cart> findAllByUserId(Long id);
}
