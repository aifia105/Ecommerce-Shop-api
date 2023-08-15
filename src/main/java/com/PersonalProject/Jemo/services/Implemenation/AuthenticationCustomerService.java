package com.PersonalProject.Jemo.services.Implemenation;


import com.PersonalProject.Jemo.config.JwtUtil;
import com.PersonalProject.Jemo.dto.CustomerDto;
import com.PersonalProject.Jemo.dto.auth.AuthenticationRequest;
import com.PersonalProject.Jemo.dto.auth.AuthenticationResponse;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.model.Customer;
import com.PersonalProject.Jemo.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationCustomerService {

    private final CustomerService customerService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(Customer customer){
        var user = CustomerDto.toEntity(customerService.save(CustomerDto.fromEntity(customer)));
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
        var user = CustomerDto.toEntity(customerService.findByEmail(request.getLogin()));
        if (user == null){
            log.warn("User not found");
            throw new EntityNotFoundException("User not found", ErrorCodes.USER_NOT_FOUND);
        }
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

}
