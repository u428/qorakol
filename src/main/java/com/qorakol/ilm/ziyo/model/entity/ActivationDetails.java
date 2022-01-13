package com.qorakol.ilm.ziyo.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "activation_details")
@Data
public class ActivationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private boolean delete = false;

    private boolean status;

    private int lessonPayed;

    @OneToOne(mappedBy = "activationDetails")
    private Activation activation;
}
