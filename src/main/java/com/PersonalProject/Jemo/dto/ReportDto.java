package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.Reporting;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ReportDto {

    private Long id;

    private UserDto rapporteur;

    private String report;

    private Instant date;

    public static ReportDto fromEntity(Reporting reporting){
        if (reporting == null){
            return null;
        }
        return ReportDto.builder()
                .id(reporting.getId())
                .rapporteur(UserDto.fromEntity(reporting.getRapporteur()))
                .report(reporting.getReport())
                .date(reporting.getDate()).build();

    }

    public static Reporting toEntity(ReportDto reportDto){
        if (reportDto == null){
            return null;
        }
        Reporting reporting = new Reporting();
        reporting.setId(reportDto.getId());
        reporting.setRapporteur(UserDto.toEntity(reportDto.getRapporteur()));
        reporting.setReport(reportDto.getReport());
        reporting.setDate(reportDto.getDate());
        return reporting;
    }
}
