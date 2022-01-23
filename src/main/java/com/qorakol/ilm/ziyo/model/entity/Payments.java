package com.qorakol.ilm.ziyo.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qorakol.ilm.ziyo.constant.PaymentStatus;
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

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "activation_detail_id")
    private Long activationDetailsId;

    @OneToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "activation_detail_id", insertable = false, updatable = false)
    private ActivationDetails activationDetails;



}
