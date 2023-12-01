package com.PersonalProject.Jemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Product extends AbstractEntity {


    @Column(name="Name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="brand")
    private String brand;


    @Column(name="priceTTC")
    private BigDecimal priceTTC;

    @Column(name="image", length=5000)
    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ItemOrderUser> itemOrderUserList;


    @OneToMany(mappedBy = "product")
    private List<MvtStk> mvtStks;

    @Column(name="ratings")
    private Integer  avg_ratings;





}
