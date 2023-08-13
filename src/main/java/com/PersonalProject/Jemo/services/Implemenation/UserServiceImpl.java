package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import com.PersonalProject.Jemo.dto.UserDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.model.User;
import com.PersonalProject.Jemo.repository.UserRepository;
import com.PersonalProject.Jemo.services.UserService;
import com.PersonalProject.Jemo.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto save(UserDto userDto) {
        List<String> errors = UserValidator.validator(userDto);
        if(!errors.isEmpty()){
            log.error("User invalid {}",userDto);
            throw new EntityNotValidException("User invalid", ErrorCodes.USER_NOT_VALID, errors);
        }
        if(userAlreadyExists(userDto.getEmail())){
            throw new EntityNotValidException("User already exists",ErrorCodes.USER_ALREADY_EXISTS, Collections.singletonList("email user already exists"));
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return UserDto.fromEntity(
                userRepository.save(
                        UserDto.toEntity(userDto)));
    }

    private Boolean userAlreadyExists(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.isPresent();
    }

    @Override
    public UserDto findById(Long id) {
        if (id == null){
            log.error("User id is null");
            return null;
        }
        return userRepository.findById(id).map(UserDto::fromEntity).orElseThrow(() -> new EntityNotFoundException(
                "User not found" + id, ErrorCodes.USER_NOT_FOUND
        ));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream().map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null){
            log.error("User id is null");
        } else {
            userRepository.deleteById(id);
        }

    }

    @Override
    public UserDto findByEmail(String email) {
        if(!StringUtils.hasLength(email)){
            log.error("User email is null");
            return null;
        }
        return userRepository.findUserByEmail(email)
                .map(UserDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("User not found" + email, ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserDto ChangePassword(ModifyPasswordDto modifyPasswordDto) {
        return null;
    }
}
