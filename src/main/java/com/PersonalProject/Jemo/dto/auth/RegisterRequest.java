package com.PersonalProject.Jemo.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String fullName;

    private String email;

    private Instant birthday;

    private String address;

    private String role;

    private String password;

    private String phone;
}
