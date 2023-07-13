package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.AddressDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AddressValidator {

    public static List<String> validator(AddressDto addressDto) {
        List<String> errors = new ArrayList<>();

        if (addressDto == null) {
            errors.add("Please fill in the address fields");
        } else {
            if (!StringUtils.hasLength(addressDto.getStreet())) {
                errors.add("Please fill in the street field");
            }
            if (!StringUtils.hasLength(addressDto.getCity())) {
                errors.add("Please fill in the city field");
            }
            if (!StringUtils.hasLength(addressDto.getState())) {
                errors.add("Please fill in the state field");
            }
            if (!StringUtils.hasLength(addressDto.getPostalCode())) {
                errors.add("Please fill in the postal code field");
            }
            if (!StringUtils.hasLength(addressDto.getCountry())) {
                errors.add("Please fill in the country field");
            }
        }
        return errors;
    }
}
