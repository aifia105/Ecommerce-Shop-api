package com.PersonalProject.Jemo.validator;

import com.PersonalProject.Jemo.dto.ReportDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ReportingValidator {

    public static List<String> validate(ReportDto reportDto){
        List<String> errors = new ArrayList<>();
        if (reportDto == null){
            errors.add("Please fill in the rating fields of the report");
        } else {
            if (reportDto.getRapporteur() == null || reportDto.getRapporteur().getId() == null){
                errors.add("No user for reporting ! user is required ");
            }
            if (!StringUtils.hasLength(reportDto.getReport())){
                errors.add("Report can not be empty");
            }
            if (reportDto.getDate() == null){
                errors.add("No date for reporting ! date is required ");
            }

        }
        return errors;
    }
}
