package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.CustomerDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import com.PersonalProject.Jemo.model.Customer;
import com.PersonalProject.Jemo.model.OrderCustomer;
import com.PersonalProject.Jemo.repository.CustomerRepository;
import com.PersonalProject.Jemo.repository.OrderCustomerRepository;
import com.PersonalProject.Jemo.services.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderCustomerRepository orderCustomerRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,OrderCustomerRepository orderCustomerRepository, PasswordEncoder passwordEncoder) {
        super();
        this.orderCustomerRepository = orderCustomerRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        List<String> errors = CustomerValidator.validator(customerDto);
        if (!errors.isEmpty()){
            log.error("Customer invalid {}",customerDto);
            throw new EntityNotValidException("Customer invalid", ErrorCodes.CUSTOMER_NOT_VALID,errors);
        }
        if (customerAlreadyExists(customerDto.getEmail())){
            throw new EntityNotValidException("customer already exists",ErrorCodes.CUSTOMER_ALREADY_EXISTS, Collections.singletonList("email customer already exists"));
        }
        customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));

        return CustomerDto.fromEntity(
                customerRepository.save(
                        CustomerDto.toEntity(customerDto)));
    }
    private Boolean customerAlreadyExists(String email){
        Optional<Customer> customer = customerRepository.findCustomerByEmail(email);
        return customer.isPresent();
    }

    @Override
    public CustomerDto findById(Long id) {
        if(id == null){
            log.error("ID is null");
            return null;
        }
        return customerRepository.findById(id)
                .map(CustomerDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("No Customer with ID"+ id , ErrorCodes.CUSTOMER_NOT_FOUND));
    }

    @Override
    public CustomerDto findByEmail(String customerEmail) {
        if(!StringUtils.hasLength(customerEmail)){
            log.error("Email is null");
            return null;
        }
        return customerRepository.findCustomerByEmail(customerEmail)
                .map(CustomerDto::fromEntity)
                .orElseThrow(()->
                        new EntityNotFoundException("No Customer with Email"+ customerEmail , ErrorCodes.CUSTOMER_NOT_FOUND));
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerRepository.findAll()
                .stream().map(CustomerDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
     if (id == null){
         log.error("ID is null");
     } else {
         List<OrderCustomer> orderCustomer = orderCustomerRepository.findAllByCustomerId(id);
         if (!orderCustomer.isEmpty()){
             throw new OperationNotValidException("can not delete a customer with a existing order",ErrorCodes.CUSTOMER_ALREADY_IN_USE);
         }
         customerRepository.deleteById(id);
     }
    }

    @Override
    public CustomerDto changePassWord(ModifyPasswordDto modifyPasswordDto) {
        validate(modifyPasswordDto);

        Optional<Customer> customerOptional = customerRepository.findById(modifyPasswordDto.getId());
        if (customerOptional.isEmpty()){
            log.warn("No customer with this ID {}",modifyPasswordDto.getId());
            throw new EntityNotFoundException("No customer with this ID" + modifyPasswordDto.getId(),ErrorCodes.CUSTOMER_NOT_FOUND);
        }
        Customer customer = customerOptional.get();
        customer.setPassword(passwordEncoder.encode(modifyPasswordDto.getPassword()));

        return CustomerDto.fromEntity(customerRepository.save(customer)) ;
    }

    private void validate(ModifyPasswordDto modifyPasswordDto){
        if (modifyPasswordDto == null){
            log.warn("Object update customer password is NULL");
            throw new OperationNotValidException("Object update customer password is NULL",ErrorCodes.CUSTOMER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (modifyPasswordDto.getId() == null){
            log.warn("Cant update Password with ID customer is NULL");
            throw new OperationNotValidException("ID customer is NULL",ErrorCodes.CUSTOMER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
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
