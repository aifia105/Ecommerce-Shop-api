package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.CartDto;
import com.PersonalProject.Jemo.repository.CartRepository;
import com.PersonalProject.Jemo.services.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    public CartServiceImpl(CartRepository cartRepository) {
        super();
        this.cartRepository = cartRepository;
    }

    @Override
    public CartDto save(CartDto cartDto) {
        return null;
    }

    @Override
    public CartDto findById(Long id) {
        return null;
    }

    @Override
    public CartDto findByUserId(Long id) {
        return null;
    }

    @Override
    public List<CartDto> findAll() {
        return cartRepository.findAll().stream()
                .map(CartDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

    }
}
