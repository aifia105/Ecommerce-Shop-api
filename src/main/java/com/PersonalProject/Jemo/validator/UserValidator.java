package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public static List<String> validator(UserDto userDto){
        List<String> errors = new ArrayList<>();

        if (userDto == null){
            errors.add("Please fill in the fields");
        } else {
            if (!StringUtils.hasLength(userDto.getName())){
                errors.add("Please fill in the name fields");
            }
            if (!StringUtils.hasLength(userDto.getLastname())){
                errors.add("Please fill in the last name fields");
            }
            if (!StringUtils.hasLength(userDto.getEmail())){
                errors.add("Please fill in the email fields");
            }
            if (!StringUtils.hasLength(userDto.getPassword())){
                errors.add("Please fill in the password fields");
            }
            if (userDto.getBirthday() == null){
                errors.add("Please fill in the birthday fields");
            }
            errors.addAll(AddressValidator.validator(userDto.getAddressDto()));
        }
        return errors;
    }
}
