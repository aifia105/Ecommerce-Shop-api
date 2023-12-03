package com.PersonalProject.Jemo.controller;

import com.PersonalProject.Jemo.controller.api.UserApi;
import com.PersonalProject.Jemo.dto.UserDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import com.PersonalProject.Jemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController implements UserApi {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDto> save(UserDto userDto) throws IOException {
        if (userDto != null) {
            byte[] bytes = userDto.getImage();
            userDto.setImage(bytes);
        } else {
            throw new IOException("CategoryDto or image data is null");
        }
        return ResponseEntity.ok(userService.save(userDto));
    }

    @Override
    public ResponseEntity<UserDto> update(Long  id, UserDto userDto) {
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @Override
    public ResponseEntity<UserDto> findById(Long  id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Override
    public ResponseEntity<UserDto> findByEmail(String customerEmail) {
        return ResponseEntity.ok(userService.findByEmail(customerEmail));
    }

    @Override
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Long  id) {
         userService.delete(id);
         return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDto> changePassWord(ModifyPasswordDto modifyPasswordDto) {
        return ResponseEntity.ok(userService.changePassWord(modifyPasswordDto));
    }
}
