package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.CustomerDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.CUSTOMER_ENDPOINT;

public interface CustomerApi {

    @PostMapping(value = CUSTOMER_ENDPOINT + "/signing" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto);

    @GetMapping(value = CUSTOMER_ENDPOINT + "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerDto> findById(@PathVariable Long id);

    @GetMapping(value = CUSTOMER_ENDPOINT + "/find/{customerEmail}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerDto> findByEmail(@PathVariable String customerEmail);

    @GetMapping(value = CUSTOMER_ENDPOINT + "/All" , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CustomerDto>> findAll();

    @DeleteMapping(value = CUSTOMER_ENDPOINT + "/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @PostMapping(value = CUSTOMER_ENDPOINT + "/updatePassword" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerDto> changePassWord(@RequestBody ModifyPasswordDto modifyPasswordDto);
}
