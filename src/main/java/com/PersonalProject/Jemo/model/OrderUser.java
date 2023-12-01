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
public class OrderUser extends AbstractEntity{


    @Column(name = "dateOrder")
    private Instant dateOrder;

    @Column(name = "total")
    private Integer total;

    @Column(name = "orderstatu")
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idCart")
    private Cart cart;

    @OneToMany(mappedBy = "orderUser")
    private List<ItemOrderUser> itemOrderUsers;





}
