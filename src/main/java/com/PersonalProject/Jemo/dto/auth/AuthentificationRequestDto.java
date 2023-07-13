package com.PersonalProject.Jemo.dto.auth;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthentificationRequestDto {

    private String login;

    private String password;
}
