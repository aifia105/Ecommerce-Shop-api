package com.PersonalProject.Jemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class ItemOrderSupplier extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "unit_price")
    private Integer unit_price;

    @ManyToOne
    @JoinColumn(name = "idOrderSupplier")
    private OrderSupplier orderSupplier;
}
