package com.PersonalProject.Jemo.dto;



import com.PersonalProject.Jemo.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserDto {

    private Long id;

    private String name;

    private String lastname;

    private String email;

    private Instant birthday;

    private String password;

    private AddressDto addressDto;

    private String picture;

    private String phone;

    public static UserDto fromEntity(User user){
        if (user == null){
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .password(user.getPassword())
                .addressDto(AddressDto.fromEntity(user.getAddress()))
                .picture(user.getPicture()).build();
    }

    public static User toEntity(UserDto userDto){
        if (userDto == null){
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setBirthday(userDto.getBirthday());
        user.setPassword(user.getPassword());
        user.setAddress(AddressDto.toEntity(userDto.getAddressDto()));
        user.setPicture(userDto.getPicture());
        return user ;
    }
}
