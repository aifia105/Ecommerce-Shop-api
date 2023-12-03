package com.PersonalProject.Jemo.services;

import com.PersonalProject.Jemo.dto.UserDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;

import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);
    UserDto update(Long id, UserDto userDto);
    UserDto findById(Long id);
    UserDto findByEmail(String customerEmail);
    List<UserDto> findAll();
    void delete(Long id);
    UserDto changePassWord(ModifyPasswordDto modifyPasswordDto);


}
