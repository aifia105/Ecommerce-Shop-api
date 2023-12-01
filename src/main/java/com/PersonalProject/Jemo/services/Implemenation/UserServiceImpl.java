package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.UserDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import com.PersonalProject.Jemo.model.User;
import com.PersonalProject.Jemo.model.OrderUser;
import com.PersonalProject.Jemo.repository.UserRepository;
import com.PersonalProject.Jemo.repository.OrderUserRepository;
import com.PersonalProject.Jemo.services.UserService;
import com.PersonalProject.Jemo.validator.CustomerValidator;
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
    private final OrderUserRepository orderUserRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, OrderUserRepository orderUserRepository, PasswordEncoder passwordEncoder) {
        super();
        this.orderUserRepository = orderUserRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto save(UserDto userDto) {
        List<String> errors = CustomerValidator.validator(userDto);
        if (!errors.isEmpty()){
            log.error("User invalid {}", userDto);
            throw new EntityNotValidException("User invalid", ErrorCodes.USER_NOT_VALID,errors);
        }
        if (customerAlreadyExists(userDto.getEmail())){
            throw new EntityNotValidException("customer already exists",ErrorCodes.CUSTOMER_ALREADY_EXISTS, Collections.singletonList("email customer already exists"));
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        System.out.println(userDto);
        return UserDto.fromEntity(
                userRepository.save(
                        UserDto.toEntity(userDto)));
    }

    @Override
    public UserDto update(String id, UserDto userDto) {
        List<String> errors = CustomerValidator.validator(userDto);
        if (!errors.isEmpty()){
            log.error("User invalid {}", userDto);
            throw new EntityNotValidException("User invalid", ErrorCodes.USER_NOT_VALID,errors);
        }
        Optional<User> optionUser = userRepository.findById(id);
        if (optionUser.isPresent()) {
            User updatetedUser = optionUser.get();
            updatetedUser.setId(userDto.getId());
            updatetedUser.setFullName(userDto.getFullName());
            updatetedUser.setBirthday(userDto.getBirthday());
            updatetedUser.setAddress(userDto.getAddress());
            updatetedUser.setRole(userDto.getRole());
            updatetedUser.setPhone(userDto.getPhone());
            updatetedUser.setEmail(userDto.getEmail());
            updatetedUser.setPassword(updatetedUser.getPassword());
            updatetedUser.setImage(userDto.getImage());
            System.out.println(updatetedUser);
            User savedUser = userRepository.save(updatetedUser);
            return UserDto.fromEntity(savedUser);
        } else  {
            log.error("User not found");
            throw  new EntityNotFoundException("No User with ID " + id, ErrorCodes.USER_NOT_FOUND);
        }

    }

    private Boolean customerAlreadyExists(String email){
        Optional<User> customer = userRepository.findUserByEmail(email);
        return customer.isPresent();
    }

    @Override
    public UserDto findById(String  id) {
        if(id == null){
            log.error("ID is null");
            return null;
        }
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("No User with ID"+ id , ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserDto findByEmail(String customerEmail) {
        if(!StringUtils.hasLength(customerEmail)){
            log.error("Email is null");
            return null;
        }
        return userRepository.findUserByEmail(customerEmail)
                .map(UserDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("No User with Email"+ customerEmail , ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream().map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String  id) {
     if (id == null){
         log.error("ID is null");
     } else {
         List<OrderUser> orderUser = orderUserRepository.findAllByUserId(id);
         if (!orderUser.isEmpty()){
             throw new OperationNotValidException("can not delete a User with a existing order");
         }
         userRepository.deleteById(id);
     }
    }

    @Override
    public UserDto changePassWord(ModifyPasswordDto modifyPasswordDto) {
        validate(modifyPasswordDto);

        Optional<User> customerOptional = userRepository.findById(modifyPasswordDto.getId());
        if (customerOptional.isEmpty()){
            log.warn("No customer with this ID {}",modifyPasswordDto.getId());
            throw new EntityNotFoundException("No User with this ID" + modifyPasswordDto.getId(),ErrorCodes.USER_NOT_FOUND);
        }
        User user = customerOptional.get();
        user.setPassword(passwordEncoder.encode(modifyPasswordDto.getPassword()));

        return UserDto.fromEntity(userRepository.save(user)) ;
    }

    private void validate(ModifyPasswordDto modifyPasswordDto){
        if (modifyPasswordDto == null){
            log.warn("Object update customer password is NULL");
            throw new OperationNotValidException("Object update User password is NULL",ErrorCodes.CUSTOMER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (modifyPasswordDto.getId() == null){
            log.warn("Cant update Password with ID customer is NULL");
            throw new OperationNotValidException("ID User is NULL",ErrorCodes.CUSTOMER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (StringUtils.hasLength(modifyPasswordDto.getPassword()) || StringUtils.hasLength(modifyPasswordDto.getConfirmPassWord())){
            log.warn("Cant update Password with Password NULL");
            throw new OperationNotValidException("Cant update Password with ID user is NULL",ErrorCodes.CUSTOMER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (!modifyPasswordDto.getPassword().equals(modifyPasswordDto.getConfirmPassWord())){
            log.warn("Cant update Password confirm password dont match");
            throw new OperationNotValidException("Cant update Password confirm password dont match",ErrorCodes.CUSTOMER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }

    }
}
