package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.CartApi;
import com.PersonalProject.Jemo.dto.CartDto;
import com.PersonalProject.Jemo.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController implements CartApi {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        super();
        this.cartService = cartService;
    }

    @Override
    public ResponseEntity<CartDto> save(CartDto cartDto) {
        return ResponseEntity.ok(cartService.save(cartDto));
    }

    @Override
    public ResponseEntity<CartDto> findById(Long  id) {
        return ResponseEntity.ok(cartService.findById(id));
    }

    @Override
    public ResponseEntity<CartDto> findByUserId(Long  id) {
        return ResponseEntity.ok(cartService.findByUserId(id));
    }

    @Override
    public ResponseEntity<List<CartDto>> findAll() {
        return ResponseEntity.ok(cartService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Long  id) {
        cartService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CartDto>> findAllByUserId(Long  id) {
        return ResponseEntity.ok(cartService.findAllByUserId(id));
    }
}
