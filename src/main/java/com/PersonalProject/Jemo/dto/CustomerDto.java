package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CustomerDto {


    private Long id;

    private String name;

    private String lastName;

    private String email;

    private Instant birthday;

    private AddressDto address;

    @JsonIgnore
    private List<CartDto> cart;

    private String picture;

    private String password;

    private String phone;

    @JsonIgnore
    private List<OrderCustomerDto> orderCustomerDtos;


    public static CustomerDto fromEntity(Customer customer){
        if(customer == null){
            return null;
        }
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .birthday(customer.getBirthday())
                .address(AddressDto.fromEntity(customer.getAddress()))
                .picture(customer.getPhone())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .phone(customer.getPhone()).build();
    }

    public static Customer toEntity(CustomerDto customerDto){
        if (customerDto == null){
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setLastName(customerDto.getLastName());
        customer.setAddress(AddressDto.toEntity(customerDto.getAddress()));
        customer.setPicture(customerDto.getPicture());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setPhone(customerDto.getPhone());
        customer.setBirthday(customerDto.getBirthday());
        return customer;
    }
}
