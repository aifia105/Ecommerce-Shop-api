package com.PersonalProject.Jemo.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;

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

    @Column(name = "ExpirationMonth")
    private Month expirationMonth;

    @Column(name = "ExpirationYear")
    private Year expirationYear;

    @Column(name = "CVV")
    private Integer cvv;

    @ManyToOne
    @JoinColumn(name = "idCart")
    private Customer customer;
}
