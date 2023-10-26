package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerValidator {

    public static List<String> validator(UserDto userDto) {
        List<String> errors = new ArrayList<>();

        if (userDto == null) {
            errors.add("Please fill in the fields");
        } else {
            if (!StringUtils.hasLength(userDto.getFullName())) {
                errors.add("Please fill in the name field");
            }
            if (!StringUtils.hasLength(userDto.getRole().toString())) {
                errors.add("Please fill in the last name field");
            }
            if (!StringUtils.hasLength(userDto.getEmail())) {
                errors.add("Please fill in the email field");
            }
            if (!StringUtils.hasLength(userDto.getPassword())) {
                errors.add("Please fill in the password field");
            }
            if (!StringUtils.hasLength(userDto.getPhone())) {
                errors.add("Please fill in the phone field");
            }
            if (!StringUtils.hasLength(userDto.getAddress())) {
                errors.add("Please fill in the address field");
            }

        }
        return errors;
    }
}
