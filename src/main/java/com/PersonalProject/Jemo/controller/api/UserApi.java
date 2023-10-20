package com.PersonalProject.Jemo.controller.api;

import com.PersonalProject.Jemo.dto.UserDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.PersonalProject.Jemo.utils.Constants.USER_ENDPOINT;

@Tag(name = "User" )
public interface UserApi {

    @PostMapping(value = USER_ENDPOINT + "/save" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> save(@RequestBody UserDto userDto);

    @GetMapping(value = USER_ENDPOINT + "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> findById(@PathVariable Long id);

    @GetMapping(value = USER_ENDPOINT + "/find/{UserEmail}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> findByEmail(@PathVariable String UserEmail);

    @GetMapping(value = USER_ENDPOINT + "/All" , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDto>> findAll();

    @DeleteMapping(value = USER_ENDPOINT + "/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @PostMapping(value = USER_ENDPOINT + "/updatePassword" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> changePassWord(@RequestBody ModifyPasswordDto modifyPasswordDto);
}
