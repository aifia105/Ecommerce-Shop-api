package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.ReportingApi;
import com.PersonalProject.Jemo.dto.ReportDto;
import com.PersonalProject.Jemo.services.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ReportingController implements ReportingApi {

    private final ReportingService reportingService;

    @Autowired
    public ReportingController(ReportingService reportingService) {
        super();
        this.reportingService = reportingService;
    }

    @Override
    public ResponseEntity<ReportDto> save(ReportDto reportDto) {
        return ResponseEntity.ok(reportingService.save(reportDto));
    }

    @Override
    public ResponseEntity<List<ReportDto>> findAllByUserId(String id) {
        return ResponseEntity.ok(reportingService.findAllByUserId(id));
    }

    @Override
    public ResponseEntity<ReportDto> findById(String id) {
        return ResponseEntity.ok(reportingService.findById(id));
    }

    @Override
    public ResponseEntity<List<ReportDto>> findAll() {
        return ResponseEntity.ok(reportingService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        reportingService.delete(id);
        return ResponseEntity.ok().build();
    }
}
