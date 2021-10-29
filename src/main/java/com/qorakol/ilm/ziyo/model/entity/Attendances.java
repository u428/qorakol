package com.qorakol.ilm.ziyo.model.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "student_attendance")
@Data
public class Attendances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "activation_id")
    private Long activationId;

    @ManyToOne()
    @JoinColumn(name = "activation_id", insertable = false, updatable = false)
    private Activation activation;






}
