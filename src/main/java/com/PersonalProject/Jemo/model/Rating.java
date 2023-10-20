package com.PersonalProject.Jemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @Column(name = "rate")
    private int rate;

    @Column(name = "date")
    private Instant date;

    @Column(name = "message")
    private String message;
}
