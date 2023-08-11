package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.ModifyPasswordDto;

import com.PersonalProject.Jemo.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.USER_ENDPOINT;

public interface UserApi {

    @PostMapping(value = USER_ENDPOINT + "/singing" ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> save(@RequestBody UserDto userDto);

    @GetMapping(value = USER_ENDPOINT + "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> findById(@PathVariable Long id);

    @GetMapping(value = USER_ENDPOINT + "All" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDto>> findAll();

    @DeleteMapping(value = USER_ENDPOINT + "/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @GetMapping(value = USER_ENDPOINT + "/find/{email}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> findByEmail(@PathVariable String email);

    @PostMapping(value = USER_ENDPOINT + "/updatePassword" ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> ChangePassword(@RequestBody ModifyPasswordDto modifyPasswordDto);
}
