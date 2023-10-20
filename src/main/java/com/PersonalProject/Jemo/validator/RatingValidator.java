package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.RatingDto;

import java.util.ArrayList;
import java.util.List;

public class RatingValidator {

    public static List<String> validate(RatingDto ratingDto) {
        List<String> errors = new ArrayList<>();
        if (ratingDto == null) {
            errors.add("Please fill in the rating fields of rating");

        } else {
            if (ratingDto.getUserDto() == null || ratingDto.getUserDto().getId() == null) {
                errors.add("No user for rating ! user is required ");
            }
            if (ratingDto.getProduct() == null || ratingDto.getProduct().getId() == null) {
                errors.add("No product for rating ! product is required ");
            }
            if (ratingDto.getRate() < 0 || ratingDto.getRate() > 5) {
                errors.add("Rate can not be 0 or more then 5 ");
            }
            if (ratingDto.getDate() == null) {
                errors.add("No date for rating ! date is required ");
            }

        }
        return errors;
    }
}
