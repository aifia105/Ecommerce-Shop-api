package com.PersonalProject.Jemo.dto.register;


import com.PersonalProject.Jemo.dto.AddressDto;
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

    private String name;

    private String lastname;

    private String email;

    private Instant birthday;

    private String password;

    private AddressDto addressDto;

    private String picture;

    private String phone;
}
