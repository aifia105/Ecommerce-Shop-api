package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.CustomerDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.APP_ROOT;

public interface CustomerApi {

    @PostMapping(value = APP_ROOT + "/Customer/signing" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto save(@RequestBody CustomerDto customerDto);

    @GetMapping(value = APP_ROOT + "/Customer/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto findById(@PathVariable Long id);

    @GetMapping(value = APP_ROOT + "/Customer/find/{customerEmail}" , produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto findByEmail(@PathVariable String customerEmail);

    @GetMapping(value = APP_ROOT + "/Customer/All" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<CustomerDto> findAll();

    @DeleteMapping(value = APP_ROOT + "Customer/delete/{id}")
    void delete(@PathVariable Long id);

    @PostMapping(value = APP_ROOT + "/Customer/updatePassword" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto changePassWord(@RequestBody ModifyPasswordDto modifyPasswordDto);
}
