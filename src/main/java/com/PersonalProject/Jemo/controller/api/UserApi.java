package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import com.PersonalProject.Jemo.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.APP_ROOT;

public interface UserApi {

    @PostMapping(value = APP_ROOT + "/User/singing" ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto save(@RequestBody UserDto userDto);

    @GetMapping(value = APP_ROOT + "/User/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto findById(@PathVariable Long id);

    @GetMapping(value = APP_ROOT + "/User/All" ,produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/User/delete/{id}")
    void delete(@PathVariable Long id);

    @GetMapping(value = APP_ROOT + "/User/find/{email}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto findByEmail(@PathVariable String email);

    @PostMapping(value = APP_ROOT + "/User/updatePassword" ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto ChangePassword(@RequestBody ModifyPasswordDto modifyPasswordDto);
}
