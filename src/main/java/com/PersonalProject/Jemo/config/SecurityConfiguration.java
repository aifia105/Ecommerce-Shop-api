package com.PersonalProject.Jemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static com.PersonalProject.Jemo.utils.Constants.AUTHENTICATION_ENDPOINT;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration  {
    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity
                 .authorizeHttpRequests((request) -> request
                         .requestMatchers("localhost:8080/JemoApi/version1/auth/customer/register").permitAll()
                         .anyRequest().authenticated()
                 )
                 .authenticationProvider(authenticationProvider)
                 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

                  return httpSecurity.build();


    }


}
