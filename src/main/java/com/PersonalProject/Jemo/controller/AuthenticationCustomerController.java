package com.PersonalProject.Jemo.controller;


import static com.PersonalProject.Jemo.utils.Constants.AUTHENTICATION_ENDPOINT;


import com.PersonalProject.Jemo.dto.auth.AuthenticationRequest;
import com.PersonalProject.Jemo.dto.auth.AuthenticationResponse;
import com.PersonalProject.Jemo.model.Customer;
import com.PersonalProject.Jemo.services.Implemenation.AuthenticationCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)
@RequiredArgsConstructor
public class AuthenticationCustomerController {

    private final AuthenticationCustomerService customerService;

    @PostMapping(value = AUTHENTICATION_ENDPOINT + "/customer/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Customer register){
        return ResponseEntity.ok(customerService.register(register));
    }

    @PostMapping(value = AUTHENTICATION_ENDPOINT + "/customer/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(customerService.authenticate(authenticationRequest));
    }
}
