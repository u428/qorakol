package com.qorakol.ilm.ziyo.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "activation")
@Data
public class Activation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "active")
    private boolean active = false;

    private boolean delete = false;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "group_id")
    private Long groupId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private Groups groups;

    @OneToOne(mappedBy = "activation")
    private ActivationDetails activationDetails;
}

