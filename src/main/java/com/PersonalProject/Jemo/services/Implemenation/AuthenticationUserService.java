package com.PersonalProject.Jemo.services.Implemenation;


import com.PersonalProject.Jemo.config.JwtUtil;
import com.PersonalProject.Jemo.dto.UserDto;
import com.PersonalProject.Jemo.dto.auth.AuthenticationRequest;
import com.PersonalProject.Jemo.dto.auth.AuthenticationResponse;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.ErrorCodes;

import com.PersonalProject.Jemo.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationUserService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDto userDto){
        var user = UserDto.toEntity(userService.save(userDto));
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        var user = UserDto.toEntity(userService.findByEmail(request.getLogin()));
        if (user == null){
            log.warn("User not found");
            throw new EntityNotFoundException("User not found", ErrorCodes.USER_NOT_FOUND);
        }
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

}
