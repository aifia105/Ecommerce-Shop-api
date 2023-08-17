package com.PersonalProject.Jemo.controller;


import static com.PersonalProject.Jemo.utils.Constants.AUTHENTICATION_ENDPOINT;


import com.PersonalProject.Jemo.dto.auth.AuthenticationRequest;
import com.PersonalProject.Jemo.dto.auth.AuthenticationResponse;
import com.PersonalProject.Jemo.model.Customer;
import com.PersonalProject.Jemo.services.Implemenation.AuthenticationCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AUTHENTICATION_ENDPOINT)
@RequiredArgsConstructor
@Tag(name = "Authentication" )
public class AuthenticationCustomerController {

    private final AuthenticationCustomerService customerService;

    @Operation(
            description = "Register new customer",
            summary = "create new customer account",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Customer register){
        return ResponseEntity.ok(customerService.register(register));
    }

    @Operation(
            description = "Authenticate",
            summary = "authenticate to your account",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid token",
                            responseCode = "403"
                    )
            }
    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(customerService.authenticate(authenticationRequest));
    }
}
