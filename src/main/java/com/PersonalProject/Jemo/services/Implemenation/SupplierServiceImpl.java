package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.SupplierDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import com.PersonalProject.Jemo.model.OrderSupplier;
import com.PersonalProject.Jemo.repository.OrderSupplierRepository;
import com.PersonalProject.Jemo.repository.SupplierRepository;
import com.PersonalProject.Jemo.services.SupplierService;
import com.PersonalProject.Jemo.validator.SupplierValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private OrderSupplierRepository orderSupplierRepository;
    public SupplierServiceImpl(SupplierRepository supplierRepository,OrderSupplierRepository orderSupplierRepository) {
        super();
        this.supplierRepository = supplierRepository;
        this.orderSupplierRepository = orderSupplierRepository;
    }
    @Override
    public SupplierDto save(SupplierDto supplierDto) {
        List<String> errors = SupplierValidator.validator(supplierDto);
        if (!errors.isEmpty()){
            log.error("invalid supplier {}",supplierDto);
            throw new EntityNotValidException("invalid supplier", ErrorCodes.SUPPLIER_NOT_VALID, errors);
        }
        return SupplierDto.fromEntity(
                supplierRepository.save(
                        SupplierDto.toEntity(supplierDto)));
    }

    @Override
    public SupplierDto findById(Integer id) {
        if (id == null){
            log.error("Supplier ID is null");
            return null;
        }
        return supplierRepository.findById(id)
                .map(SupplierDto::fromEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException("no supplier with the ID" + id
                                ,ErrorCodes.SUPPLIER_NOT_FOUND));
    }

    @Override
    public List<SupplierDto> findAll() {
        return supplierRepository.findAll().stream()
                .map(SupplierDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("supplier ID is null");
        } else {
         List<OrderSupplier> orderSupplier = orderSupplierRepository.findAllBySupplierId(id);
         if(!orderSupplier.isEmpty()){
             throw new OperationNotValidException("can not delete a Supplier with a existing order",ErrorCodes.SUPPLIER_ALREADY_IN_USE);
         }
         supplierRepository.deleteById(id);
        }

    }
}
