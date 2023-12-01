package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.CartDto;


import java.util.List;

public interface CartService {

    CartDto save(CartDto cartDto);
    CartDto findById(String  id);
    CartDto findByUserId(String  id);
    List<CartDto> findAllByUserId(String  id);
    List<CartDto> findAll();
    void delete(String  id);

}
