package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import com.PersonalProject.Jemo.dto.UserDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.repository.UserRepository;
import com.PersonalProject.Jemo.services.UserService;
import com.PersonalProject.Jemo.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDto save(UserDto userDto) {
        List<String> errors = UserValidator.validator(userDto);
        if(!errors.isEmpty()){
            log.error("User invalid {}",userDto);
            throw new EntityNotValidException("User invalid", ErrorCodes.USER_NOT_VALID, errors);
        }
        return UserDto.fromEntity(
                userRepository.save(
                        UserDto.toEntity(userDto)));
    }

    @Override
    public UserDto findById(Integer id) {
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
    public void delete(Integer id) {
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
