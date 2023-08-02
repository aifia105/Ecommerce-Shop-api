package com.PersonalProject.Jemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Customer extends AbstractEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Embedded
    private Address address;

    @Embedded
    private Cart cart;

    @Column(name = "picture")
    private String picture;


    @Column(unique = true ,name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<OrderCustomer> orderCustomers;


}
