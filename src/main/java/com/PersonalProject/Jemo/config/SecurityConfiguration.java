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
                 .csrf()
                 .disable()
                 .authorizeHttpRequests((request) -> request
                         .requestMatchers("/JemoApi/version1/auth/**",
                                 "/v3/api-docs/**",
                                 "/swagger-resources/**",
                                 "/configuration/ui",
                                 "/configuration/security",
                                 "/swagger-ui/**",
                                 "/webjars/**",
                                 "/swagger-ui.html"
                                 ).permitAll()
                         .anyRequest().authenticated()
                 )
                 .sessionManagement()
                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and()
                 .authenticationProvider(authenticationProvider)
                 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

                  return httpSecurity.build();


    }


}
