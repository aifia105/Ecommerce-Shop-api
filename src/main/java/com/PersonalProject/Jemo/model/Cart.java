package com.PersonalProject.Jemo.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Cart extends AbstractEntity {

    @Column(name = "CardNumber")
    private BigDecimal cardNumber;

    @Column(name = "CardholderName")
    private String cardholderName;

    @Column(name = "ExpirationDate")
    private Instant expirationDate;

    @Column(name = "CVV")
    private Integer cvv;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<OrderUser> orderUsers;
}
