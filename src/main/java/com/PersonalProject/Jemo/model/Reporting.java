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


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Reporting extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "rapporteurId")
    private User rapporteur;

    @Column(name = "report")
    private String report;

    @Column(name = "date")
    private Instant date;
}
