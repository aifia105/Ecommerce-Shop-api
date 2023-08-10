package com.PersonalProject.Jemo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    @Column(name="CreationDate" , nullable = false)
    @JsonIgnore
    private Instant CreationDate;

    @LastModifiedDate
    @Column(name="LastUpDate")
    @JsonIgnore
    private Instant LastUpDate;


}
