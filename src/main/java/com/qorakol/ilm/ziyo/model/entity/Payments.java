package com.qorakol.ilm.ziyo.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student_payment")
@Data
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "summa")
    private double summa;

    @Temporal(TemporalType.DATE)
    @CreatedDate
    @Column
    private Date time;

    @Column(name = "qolgan_darsi")
    private double qolganDarsi;

    @Column(name = "dars_soati")
    private double darsSoati;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "group_id")
    private Long groupId;

    @OneToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @OneToOne
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private Groups groups;



}
