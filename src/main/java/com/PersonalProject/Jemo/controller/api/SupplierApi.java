package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.SupplierDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.SUPPLIER_ENDPOINT;

public interface SupplierApi {


    @PostMapping(value = SUPPLIER_ENDPOINT + "/create" ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    SupplierDto save(@RequestBody SupplierDto supplierDto);

    @GetMapping(value = SUPPLIER_ENDPOINT + "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    SupplierDto findById(@PathVariable Long id);

    @GetMapping(value = SUPPLIER_ENDPOINT + "/All" ,produces = MediaType.APPLICATION_JSON_VALUE)
    List<SupplierDto> findAll();

    @DeleteMapping(value = SUPPLIER_ENDPOINT + "/delete/{id}")
    void delete(@PathVariable Long id);
}
