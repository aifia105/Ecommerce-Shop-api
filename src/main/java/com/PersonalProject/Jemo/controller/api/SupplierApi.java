package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.SupplierDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.APP_ROOT;

public interface SupplierApi {


    @PostMapping(value = APP_ROOT + "/Supplier/create" ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    SupplierDto save(@RequestBody SupplierDto supplierDto);

    @GetMapping(value = APP_ROOT + "/Supplier/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    SupplierDto findById(@PathVariable Long id);

    @GetMapping(value = APP_ROOT + "/Supplier/All" ,produces = MediaType.APPLICATION_JSON_VALUE)
    List<SupplierDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/Supplier/delete/{id}")
    void delete(@PathVariable Long id);
}
