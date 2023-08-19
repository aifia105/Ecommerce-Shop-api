package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.CustomerDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerValidator {

    public static List<String> validator(CustomerDto customerDto) {
        List<String> errors = new ArrayList<>();

        if (customerDto == null) {
            errors.add("Please fill in the fields");
        } else {
            if (!StringUtils.hasLength(customerDto.getName())) {
                errors.add("Please fill in the name field");
            }
            if (!StringUtils.hasLength(customerDto.getLastName())) {
                errors.add("Please fill in the last name field");
            }
            if (!StringUtils.hasLength(customerDto.getEmail())) {
                errors.add("Please fill in the email field");
            }
            if (!StringUtils.hasLength(customerDto.getPassword())) {
                errors.add("Please fill in the password field");
            }
            if (!StringUtils.hasLength(customerDto.getPhone())) {
                errors.add("Please fill in the phone field");
            }
            errors.addAll(AddressValidator.validator(customerDto.getAddress()));

        }
        return errors;
    }
}
