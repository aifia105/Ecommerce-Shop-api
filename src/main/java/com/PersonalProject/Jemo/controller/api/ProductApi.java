package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.ItemOrderUserDto;
import com.PersonalProject.Jemo.dto.ProductDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.PRODUCT_ENDPOINT;

@Tag(name = "Products" )
public interface ProductApi {

    @PostMapping(value =PRODUCT_ENDPOINT + "/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto) throws IOException;

    @GetMapping(value =PRODUCT_ENDPOINT + "/byName/{productName}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDto> findByName(@PathVariable String productName);

    @GetMapping(value =PRODUCT_ENDPOINT + "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDto> findById(@PathVariable Long id);

    @GetMapping(value =PRODUCT_ENDPOINT + "/All",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductDto>> findAll();

    @GetMapping(value =PRODUCT_ENDPOINT + "/filter/Category/{idCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductDto>> findAllByCategoryId(@PathVariable Long idCategory);

    @GetMapping(value =PRODUCT_ENDPOINT + "/History/OrderUser/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ItemOrderUserDto>> findHistoryOrderUser(@PathVariable Long id);


    @DeleteMapping(value =PRODUCT_ENDPOINT + "/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable  Long id);
}
