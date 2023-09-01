package com.qorakol.ilm.ziyo.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student_attendance")
@Data
public class Attendances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    private boolean delete = false;

    private boolean counted = false;

    @JsonIgnore
    @Column(name = "activation_id")
    private Long activationId;

    @Column(name = "free_lesson")
    private boolean freeLesson = false;

    @Temporal(TemporalType.DATE)
    @CreatedDate
    @Column
    private Date time;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "activation_id", insertable = false, updatable = false)
    private Activation activation;






}
