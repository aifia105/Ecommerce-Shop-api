package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.UserDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;

import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);
    UserDto update(String id, UserDto userDto);
    UserDto findById(String id);
    UserDto findByEmail(String customerEmail);
    List<UserDto> findAll();
    void delete(String id);
    UserDto changePassWord(ModifyPasswordDto modifyPasswordDto);


}
