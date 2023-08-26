package com.PersonalProject.Jemo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Customer extends AbstractEntity implements UserDetails {

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Embedded
    private Address address;

    @OneToMany( mappedBy = "customer")
    private List<Cart> cart;

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

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("client"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
