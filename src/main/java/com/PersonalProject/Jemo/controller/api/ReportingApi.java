package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.ReportDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.PersonalProject.Jemo.utils.Constants.REPORT_ENDPOINT;

@Tag(name = "Reporting")
public interface ReportingApi {

    @PostMapping(value =REPORT_ENDPOINT + "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    ResponseEntity<ReportDto> save(@RequestBody ReportDto reportDto);

    @GetMapping(value =REPORT_ENDPOINT + "/filter/user/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ReportDto>> findAllByUserId(@PathVariable String id);

    @GetMapping(value =REPORT_ENDPOINT + "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ReportDto> findById(@PathVariable String id);

    @GetMapping(value =REPORT_ENDPOINT + "/All",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ReportDto>> findAll();

    @DeleteMapping(value =REPORT_ENDPOINT + "/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable String id);
}
