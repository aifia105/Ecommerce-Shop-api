package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.ItemOrderCustomerDto;
import com.PersonalProject.Jemo.dto.ItemOrderSupplierDto;
import com.PersonalProject.Jemo.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.APP_ROOT;
public interface ProductApi {

    @PostMapping(value =APP_ROOT + "/Product/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    ProductDto save(@RequestBody ProductDto productDto);

    @GetMapping(value =APP_ROOT + "/Product/{productName}",produces = MediaType.APPLICATION_JSON_VALUE)
    ProductDto findByName(@PathVariable String productName);

    @GetMapping(value =APP_ROOT + "/Product/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ProductDto findById(@PathVariable Long id);

    @GetMapping(value =APP_ROOT + "/Product/All",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProductDto> findAll();

    @GetMapping(value =APP_ROOT + "/Product/filter/Category/{idCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProductDto> findAllByCategoryId(@PathVariable Long idCategory);

    @GetMapping(value =APP_ROOT + "/Product/History/OrderCustomer/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ItemOrderCustomerDto> findHistoryOrderClient(@PathVariable Long id);

    @GetMapping(value =APP_ROOT + "/Product/History/OrderSupplier/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    List<ItemOrderSupplierDto> findHistoryOrderSupplier(@PathVariable Long id);

    @DeleteMapping(value =APP_ROOT + "/Product/delete/{id}")
    void delete(@PathVariable  Long id);
}
