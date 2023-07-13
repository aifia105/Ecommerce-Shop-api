package com.PersonalProject.Jemo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Supplier extends AbstractEntity {

    @Column(name="name")
    private String name;

    @Column(name="address")
    private Address address;

    @Column(name="mail")
    private String mail;

    @Column(name="phone")
    private String phone;

    @OneToMany(mappedBy = "supplier")
    private List<OrderSupplier> orderSuppliers;
}
