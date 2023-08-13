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


    @Column(name="codeProduct")
    private String codeProduct;

    @Column(name="Name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="priceHT")
    private BigDecimal priceHT;

    @Column(name="TVA")
    private BigDecimal TVA;

    @Column(name="priceTTC")
    private BigDecimal priceTTC;

    @Column(name="picture")
    private String picture;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ItemOrderCustomer> itemOrderCustomerList;

    @OneToMany(mappedBy = "product")
    private List<ItemOrderSupplier> itemOrderSupplierListList;

    @OneToMany(mappedBy = "product")
    private List<MvtStk> mvtStks;



}
