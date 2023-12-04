package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.ProductDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductValidator {

    public static List<String> validator(ProductDto productDto){
        List<String> errors = new ArrayList<>();
        if (productDto == null){
            errors.add("Please fill in the product fields");
        } else {
            if (!StringUtils.hasLength(productDto.getName())){
                errors.add("Please fill in the product name field");
            }
            if (!StringUtils.hasLength(productDto.getDescription())){
                errors.add("Please fill in the product description field");
            }
            if (!StringUtils.hasLength(productDto.getBrand())){
                errors.add("Please fill in the product brand");
            }
            if (productDto.getPriceTTC().compareTo(BigDecimal.ZERO) == 0){
                errors.add("Please fill in the product priceTTC");
            }
            if (productDto.getCategory() == null || productDto.getCategory().getId() == null){
                errors.add("Please select product category");
            }
            if (productDto.getRating() == null) {
                errors.add("Please add product rate");
            }
        }
        return errors;
    }
}
