package com.qorakol.ilm.ziyo.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "payment_details")
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
