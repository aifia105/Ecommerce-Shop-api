package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.CartDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.PersonalProject.Jemo.utils.Constants.CART_ENDPOINT;


@Tag(name = "Cart Management")
public interface CartApi {


    @PostMapping(value = CART_ENDPOINT + "/create" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CartDto> save(@RequestBody CartDto cartDto);

    @GetMapping(value = CART_ENDPOINT + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CartDto> findById(@PathVariable Long id);

    @GetMapping(value = CART_ENDPOINT + "/filter/Customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CartDto> findByUserId(@PathVariable Long id);

    @GetMapping(value = CART_ENDPOINT + "/All", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CartDto>> findAll();

    @DeleteMapping (value = CART_ENDPOINT + "/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
