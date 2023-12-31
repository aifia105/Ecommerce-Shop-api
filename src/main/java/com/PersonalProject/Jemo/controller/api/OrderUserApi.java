package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.ItemOrderUserDto;
import com.PersonalProject.Jemo.dto.OrderUserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.ORDER_USER_ENDPOINT;

@Tag(name = "User Order" )
public interface OrderUserApi {


    @PostMapping(value = ORDER_USER_ENDPOINT + "/create" ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderUserDto> save(@RequestBody OrderUserDto orderUserDto);

    @GetMapping(value = ORDER_USER_ENDPOINT + "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderUserDto> findById(@PathVariable Long id);

    @GetMapping(value = ORDER_USER_ENDPOINT + "/All" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderUserDto>> findAll();

    @DeleteMapping(value = ORDER_USER_ENDPOINT + "/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @GetMapping(value = ORDER_USER_ENDPOINT + "/filter/Order/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderUserDto>> findAllByOrderId(@PathVariable Long id);

    @PatchMapping(value = ORDER_USER_ENDPOINT + "/update/{id}/OrderStatus/{orderStatus}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderUserDto> updateOrderStatus(@PathVariable Long id, @PathVariable String orderStatus);

    @PatchMapping(value = ORDER_USER_ENDPOINT + "/update/{id}/item/{idItem}/Quantity/{quantity}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderUserDto> updateQuantityOrder(@PathVariable Long id, @PathVariable Long idItem, @PathVariable BigDecimal quantity);

    @PatchMapping(value = ORDER_USER_ENDPOINT + "/update/{id}/Customer/{idUser}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderUserDto> updateUser(@PathVariable Long id, @PathVariable Long idUser);

    @PatchMapping(value = ORDER_USER_ENDPOINT + "/update/{id}/item/{idItem}/product/{idProduct}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderUserDto> updateProduct(@PathVariable Long id, @PathVariable Long idItem , @PathVariable Long idProduct);

    @DeleteMapping(value = ORDER_USER_ENDPOINT + "/delete/{id}/item/{idItem}")
    ResponseEntity<OrderUserDto> deleteProduct(@PathVariable Long  id, @PathVariable Long  idItem);
}
