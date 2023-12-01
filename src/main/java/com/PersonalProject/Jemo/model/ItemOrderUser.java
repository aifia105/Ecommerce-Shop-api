package com.PersonalProject.Jemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class ItemOrderUser extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "total")
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "idOrderUser")
    private OrderUser orderUser;
}
