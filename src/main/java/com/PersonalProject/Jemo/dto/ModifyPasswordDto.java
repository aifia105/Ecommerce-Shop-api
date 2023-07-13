package com.PersonalProject.Jemo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModifyPasswordDto {

    private Integer id;

    private String password;

    private String confirmPassWord;
}
