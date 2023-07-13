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
public class OrderSupplier extends AbstractEntity {

    @Column(name="code")
    private String code;

    @Column(name="dateOrder")
    private Instant dateOrder;

    @Column(name="orderstatu")
    @Enumerated(EnumType.STRING)
    private OrderStatu orderStatu;

    @ManyToOne
    @JoinColumn(name = "idsupplier")
    private Supplier supplier;

    @OneToMany(mappedBy = "orderSupplier")
    private List<ItemOrderSupplier> orderSuppliers;
}
