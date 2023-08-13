package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.SupplierApi;
import com.PersonalProject.Jemo.dto.SupplierDto;
import com.PersonalProject.Jemo.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierController implements SupplierApi {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        super();
        this.supplierService = supplierService;
    }

    @Override
    public ResponseEntity<SupplierDto> save(SupplierDto supplierDto) {
        return ResponseEntity.ok(supplierService.save(supplierDto));
    }

    @Override
    public ResponseEntity<SupplierDto> findById(Long id) {
        return ResponseEntity.ok(supplierService.findById(id));
    }

    @Override
    public ResponseEntity<List<SupplierDto>> findAll() {
        return ResponseEntity.ok(supplierService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
         supplierService.delete(id);
         return ResponseEntity.ok().build();
    }
}
