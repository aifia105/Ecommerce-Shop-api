package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.SupplierApi;
import com.PersonalProject.Jemo.dto.SupplierDto;
import com.PersonalProject.Jemo.services.SupplierService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierController implements SupplierApi {

    private SupplierService supplierService;
    public SupplierController(SupplierService supplierService) {
        super();
        this.supplierService = supplierService;
    }

    @Override
    public SupplierDto save(SupplierDto supplierDto) {
        return supplierService.save(supplierDto);
    }

    @Override
    public SupplierDto findById(Long id) {
        return supplierService.findById(id);
    }

    @Override
    public List<SupplierDto> findAll() {
        return supplierService.findAll();
    }

    @Override
    public void delete(Long id) {
         supplierService.delete(id);
    }
}
