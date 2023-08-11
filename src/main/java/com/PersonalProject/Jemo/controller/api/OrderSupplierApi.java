package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.OrderSupplierDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.ORDER_SUPPLIER_ENDPOINT;

public interface OrderSupplierApi {

    @PostMapping(value = ORDER_SUPPLIER_ENDPOINT + "/create" ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderSupplierDto> save(@RequestBody OrderSupplierDto orderSupplierDto);

    @GetMapping(value = ORDER_SUPPLIER_ENDPOINT + "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderSupplierDto> findById(@PathVariable Long id);

    @GetMapping(value = ORDER_SUPPLIER_ENDPOINT + "/All" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderSupplierDto>>  findAll();

    @DeleteMapping(value = ORDER_SUPPLIER_ENDPOINT + "/delete/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable Long id);
}
