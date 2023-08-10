package com.PersonalProject.Jemo.services.Implemenation;

import com.PersonalProject.Jemo.dto.CustomerDto;
import com.PersonalProject.Jemo.dto.ModifyPasswordDto;
import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import com.PersonalProject.Jemo.model.OrderCustomer;
import com.PersonalProject.Jemo.repository.CustomerRepository;
import com.PersonalProject.Jemo.repository.OrderCustomerRepository;
import com.PersonalProject.Jemo.services.CustomerService;
import com.PersonalProject.Jemo.validator.CustomerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private OrderCustomerRepository orderCustomerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,OrderCustomerRepository orderCustomerRepository) {
        super();
        this.orderCustomerRepository = orderCustomerRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        List<String> errors = CustomerValidator.validator(customerDto);
        if (!errors.isEmpty()){
            log.error("Customer invalid {}",customerDto);
            throw new EntityNotValidException("Customer invalid", ErrorCodes.CUSTOMER_NOT_VALID,errors);
        }
        return CustomerDto.fromEntity(
                customerRepository.save(
                        CustomerDto.toEntity(customerDto)));
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
        return null;
    }
}
