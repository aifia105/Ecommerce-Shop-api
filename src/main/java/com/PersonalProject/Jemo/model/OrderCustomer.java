package com.PersonalProject.Jemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class OrderCustomer extends AbstractEntity{

    @Column(name = "codeOrder")
    private String codeOrder;

    @Column(name = "dateOrder")
    private Instant dateOrder;

    @Column(name = "total")
    private Integer total;

    @Column(name = "orderstatu")
    @Enumerated(EnumType.STRING)
    private OrderStatu orderStatu;

    @ManyToOne
    @JoinColumn(name = "idCustomer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "idCart")
    private Cart cart;

    @OneToMany(mappedBy = "orderCustomer")
    private List<ItemOrderCustomer> orderItemCustomers;





}
