package com.qorakol.ilm.ziyo.model.entity;

import lombok.Data;
import org.hibernate.annotations.CollectionId;

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

    @Column(name = "activation_id")
    private Long activationId;

    @OneToOne()
    @JoinColumn(name = "activation_id", insertable = false, updatable = false)
    private Activation activation;

    public void lessonPayedDecrease(){
        this.lessonPayed--;
    }

}
