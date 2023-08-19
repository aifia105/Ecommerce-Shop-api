package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.ItemOrderCustomerDto;
import com.PersonalProject.Jemo.dto.OrderCustomerDto;
import com.PersonalProject.Jemo.model.OrderStatu;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.ORDER_CUSTOMER_ENDPOINT;

@Tag(name = "Customer Order" )
public interface OrderCustomerApi {


    @PostMapping(value = ORDER_CUSTOMER_ENDPOINT + "/create" ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderCustomerDto> save(@RequestBody OrderCustomerDto orderCustomerDto);

    @GetMapping(value = ORDER_CUSTOMER_ENDPOINT + "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderCustomerDto> findById(@PathVariable Long id);

    @GetMapping(value = ORDER_CUSTOMER_ENDPOINT + "/All" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderCustomerDto>> findAll();

    @DeleteMapping(value = ORDER_CUSTOMER_ENDPOINT + "/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @GetMapping(value = ORDER_CUSTOMER_ENDPOINT + "/filter/Order/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ItemOrderCustomerDto>> findAllByOrderId(@PathVariable Long id);

    @PatchMapping(value = ORDER_CUSTOMER_ENDPOINT + "/update/{id}/OrderStatus/{orderStatu}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderCustomerDto> updateOrderStatus(@PathVariable Long id,@PathVariable OrderStatu orderStatu);

    @PatchMapping(value = ORDER_CUSTOMER_ENDPOINT + "/update/{id}/item/{idItem}/Quantity/{quantity}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderCustomerDto> updateQuantityOrder(@PathVariable Long id,@PathVariable Long idItem,@PathVariable BigDecimal quantity);

    @PatchMapping(value = ORDER_CUSTOMER_ENDPOINT + "/update/{id}/Customer/{idCustomer}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderCustomerDto> updateCustomer(@PathVariable Long id,@PathVariable Long idCustomer);

    @PatchMapping(value = ORDER_CUSTOMER_ENDPOINT + "/update/{id}/item/{idItem}/product/{idProduct}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderCustomerDto> updateProduct(@PathVariable Long id,@PathVariable Long idItem ,@PathVariable Long idProduct);

    @DeleteMapping(value = ORDER_CUSTOMER_ENDPOINT + "/delete/{id}/item/{idItem}")
    ResponseEntity<OrderCustomerDto> deleteProduct(@PathVariable Long id,@PathVariable Long idItem);
}
