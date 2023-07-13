package com.PersonalProject.Jemo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Cart implements Serializable {

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
}
