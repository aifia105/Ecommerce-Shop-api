package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.CartDto;

import java.util.List;

public interface CartService {

    CartDto save(CartDto cartDto);
    CartDto findById(Long id);
    CartDto findByUserId(Long id);
    List<CartDto> findAll();
    void delete(Long id);

}
