package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.ItemOrderSupplierDto;
import com.PersonalProject.Jemo.dto.OrderSupplierDto;
import com.PersonalProject.Jemo.model.OrderStatu;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.PersonalProject.Jemo.utils.Constants.ORDER_SUPPLIER_ENDPOINT;

@Tag(name = "Supplier Order" )
public interface OrderSupplierApi {

    @PostMapping(value = ORDER_SUPPLIER_ENDPOINT + "/create" ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderSupplierDto> save(@RequestBody OrderSupplierDto orderSupplierDto);

    @GetMapping(value = ORDER_SUPPLIER_ENDPOINT + "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderSupplierDto> findById(@PathVariable Long id);

    @GetMapping(value = ORDER_SUPPLIER_ENDPOINT + "/All" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OrderSupplierDto>>  findAll();

    @DeleteMapping(value = ORDER_SUPPLIER_ENDPOINT + "/delete/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable Long id);

    @GetMapping(value = ORDER_SUPPLIER_ENDPOINT + "/filter/Order/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ItemOrderSupplierDto>> findAllByOrderId(@PathVariable Long id);

    @PatchMapping(value = ORDER_SUPPLIER_ENDPOINT + "/update/{id}/OrderStatus/{orderStatu}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderSupplierDto> updateOrderStatus(@PathVariable Long id, @PathVariable OrderStatu orderStatu);

    @PatchMapping(value = ORDER_SUPPLIER_ENDPOINT + "/update/{id}/item/{idItem}/Quantity/{quantity}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderSupplierDto> updateQuantityOrder(@PathVariable Long id,@PathVariable Long idItem,@PathVariable BigDecimal quantity);

    @PatchMapping(value = ORDER_SUPPLIER_ENDPOINT + "/update/{id}/Customer/{idSupplier}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderSupplierDto> updateCustomer(@PathVariable Long id,@PathVariable Long idSupplier);

    @PatchMapping(value = ORDER_SUPPLIER_ENDPOINT + "/update/{id}/item/{idItem}/product/{idProduct}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderSupplierDto> updateProduct(@PathVariable Long id,@PathVariable Long idItem ,@PathVariable Long idProduct);

    @DeleteMapping(value = ORDER_SUPPLIER_ENDPOINT + "/delete/{id}/item/{idItem}")
    ResponseEntity<OrderSupplierDto> deleteProduct(@PathVariable Long id,@PathVariable Long idItem);
}
