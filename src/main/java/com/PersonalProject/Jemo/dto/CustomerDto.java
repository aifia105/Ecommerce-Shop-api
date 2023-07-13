package com.PersonalProject.Jemo.dto;


import com.PersonalProject.Jemo.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerDto {


    private Integer id;

    private String name;

    private String lastName;

    private AddressDto address;

    private CartDto cart;

    private String picture;

    private String email;

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
                .address(AddressDto.fromEntity(customer.getAddress()))
                .cart(CartDto.fromEntity(customer.getCart()))
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
        customer.setCart(CartDto.toEntity(customerDto.getCart()));
        customer.setPicture(customerDto.getPicture());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setPhone(customerDto.getPhone());
        return customer;
    }
}
