package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.ReportDto;

import java.util.List;

public interface ReportingService {

    ReportDto save(ReportDto reportDto);

    List<ReportDto> findAllByUserId(Long id);


    ReportDto findById(Long id);

    List<ReportDto> findAll();

    void delete(Long id);
}
