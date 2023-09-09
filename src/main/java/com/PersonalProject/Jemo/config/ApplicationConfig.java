package com.PersonalProject.Jemo.config;



import com.PersonalProject.Jemo.model.Customer;
import com.PersonalProject.Jemo.model.User;
import com.PersonalProject.Jemo.repository.CustomerRepository;
import com.PersonalProject.Jemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {


    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            Optional<User> userOptional = userRepository.findUserByEmail(username);
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(username);
            if (userOptional.isEmpty()) {
                return customerOptional.get();
            }
            else  {
                return userOptional.get();
            }
        };


    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }
}
