package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validator(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();

        if (categoryDto == null) {
            errors.add("Please fill in category fields");
        } else {

            if (!StringUtils.hasLength(categoryDto.getCodeCategory())) {
                errors.add("Please fill in the category code");
            }
            if (!StringUtils.hasLength(categoryDto.getNameCategory())) {
                errors.add("Please fill in the category name");
            }

        }
        return errors;
    }

}



