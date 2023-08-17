package com.PersonalProject.Jemo.controller;


import com.PersonalProject.Jemo.config.JwtUtil;
import com.PersonalProject.Jemo.dto.UserDto;
import com.PersonalProject.Jemo.repository.UserRepository;
import com.PersonalProject.Jemo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.PersonalProject.Jemo.utils.Constants.AUTHENTICATION_ENDPOINT;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AuthenticationUserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;


    @PostMapping()
    public ResponseEntity<String> register(@RequestBody final UserDto user){
        return ResponseEntity.ok(jwtUtil.generateToken((UserDetails) userService.save(user)));
    }
}
