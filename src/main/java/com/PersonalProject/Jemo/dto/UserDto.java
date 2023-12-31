package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserDto {


    private Long id;

    private String fullName;

    private String email;

    private Instant birthday;

    private String address;

    private String role;

    private byte[] image;

    private String password;

    private String phone;







    public static UserDto fromEntity(User user){
        if(user == null){
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .role(user.getRole())
                .birthday(user.getBirthday())
                .address(user.getAddress())
                .image(user.getImage())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone()).build();
    }

    public static User toEntity(UserDto userDto){
        if (userDto == null){
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setFullName(userDto.getFullName());
        user.setRole(userDto.getRole());
        user.setAddress(userDto.getAddress());
        user.setImage(userDto.getImage());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setBirthday(userDto.getBirthday());
        return user;
    }
}
