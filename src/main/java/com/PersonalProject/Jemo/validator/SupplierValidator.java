package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.SupplierDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SupplierValidator {

    public static List<String> validator(SupplierDto supplierDto){
        List<String> errors = new ArrayList<>();
        if (supplierDto == null){
            errors.add("Please fill in the fields");
        } else {
            if (StringUtils.hasLength(supplierDto.getName())){
                errors.add("Please fill in the name field");
            }
            if (StringUtils.hasLength(supplierDto.getMail())){
                errors.add("Please fill in the mail field");
            }
            if (StringUtils.hasLength(supplierDto.getPhone())){
                errors.add("Please fill in the phone field");
            }
            errors.addAll(AddressValidator.validator(supplierDto.getAddress()));
        }
        return errors;
    }
}
