package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.UserApi;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import com.PersonalProject.Jemo.dto.UserDto;
import com.PersonalProject.Jemo.services.UserService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UserApi {

    private UserService userService;
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @Override
    public UserDto save(UserDto userDto) {
        return userService.save(userDto);
    }

    @Override
    public UserDto findById(Long id) {
        return userService.findById(id);
    }

    @Override
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public UserDto findByEmail(String email) {
        return userService.findByEmail(email);
    }

    @Override
    public UserDto ChangePassword(ModifyPasswordDto modifyPasswordDto) {
        return userService.ChangePassword(modifyPasswordDto);
    }
}
