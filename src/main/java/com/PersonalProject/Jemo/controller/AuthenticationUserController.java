package com.PersonalProject.Jemo.controller;


import static com.PersonalProject.Jemo.utils.Constants.APP_ROOT;



import com.PersonalProject.Jemo.dto.UserDto;
import com.PersonalProject.Jemo.dto.auth.AuthenticationRequest;
import com.PersonalProject.Jemo.dto.auth.AuthenticationResponse;

import com.PersonalProject.Jemo.services.Implemenation.AuthenticationUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication User" )
public class AuthenticationUserController {

    private final AuthenticationUserService userService;

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
    @PostMapping(APP_ROOT + "/singin")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto register){
        return ResponseEntity.ok(userService.register(register));
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
    @PostMapping(APP_ROOT + "/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(userService.authenticate(authenticationRequest));
    }
}
