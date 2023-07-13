package com.PersonalProject.Jemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class ItemOrderCustomer extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private Integer unit_price;

    @ManyToOne
    @JoinColumn(name = "idOrderCustomer")
    private OrderCustomer orderCustomer;
}
