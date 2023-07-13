package com.PersonalProject.Jemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Category extends AbstractEntity {

    @Column(name="codeCategory")
    private String codeCategory;

    @Column(name="nameCategory")
    private String nameCategory;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
