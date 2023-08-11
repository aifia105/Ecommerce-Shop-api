package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.OrderCustomerDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.ORDER_CUSTOMER_ENDPOINT;
import static com.PersonalProject.Jemo.utils.Constants.ORDER_SUPPLIER_ENDPOINT;

public interface OrderCustomerApi {


    @PostMapping(value = ORDER_SUPPLIER_ENDPOINT + "/create" ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderCustomerDto> save(@RequestBody OrderCustomerDto orderCustomerDto);

    @GetMapping(value = ORDER_CUSTOMER_ENDPOINT + "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderCustomerDto> findById(@PathVariable Long id);

    @GetMapping(value = ORDER_CUSTOMER_ENDPOINT + "/All" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderCustomerDto>> findAll();

    @DeleteMapping(value = ORDER_CUSTOMER_ENDPOINT + "/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
