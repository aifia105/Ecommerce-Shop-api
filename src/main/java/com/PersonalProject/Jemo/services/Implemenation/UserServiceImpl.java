package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import com.PersonalProject.Jemo.dto.UserDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import com.PersonalProject.Jemo.model.User;
import com.PersonalProject.Jemo.repository.UserRepository;
import com.PersonalProject.Jemo.services.UserService;
import com.PersonalProject.Jemo.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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
        validate(modifyPasswordDto);

        Optional<User> userOptional = userRepository.findById(modifyPasswordDto.getId());
        if (userOptional.isEmpty()){
            log.warn("No user with this ID {}",modifyPasswordDto.getId());
            throw new EntityNotFoundException("No user with this ID" + modifyPasswordDto.getId(),ErrorCodes.USER_NOT_FOUND);
        }
        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(modifyPasswordDto.getPassword()));

        return UserDto.fromEntity(userRepository.save(user));
    }
    private void validate(ModifyPasswordDto modifyPasswordDto){
        if (modifyPasswordDto == null){
            log.warn("Object update user password is NULL");
            throw new OperationNotValidException("Object update user password is NULL",ErrorCodes.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (modifyPasswordDto.getId() == null){
            log.warn("Cant update Password with ID user is NULL");
            throw new OperationNotValidException("ID user is NULL",ErrorCodes.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (StringUtils.hasLength(modifyPasswordDto.getPassword()) || StringUtils.hasLength(modifyPasswordDto.getConfirmPassWord())){
            log.warn("Cant update Password with Password NULL");
            throw new OperationNotValidException("Cant update Password with ID user is NULL",ErrorCodes.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (!modifyPasswordDto.getPassword().equals(modifyPasswordDto.getConfirmPassWord())){
            log.warn("Cant update Password confirm password dont match");
            throw new OperationNotValidException("Cant update Password confirm password dont match",ErrorCodes.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }

    }
}
