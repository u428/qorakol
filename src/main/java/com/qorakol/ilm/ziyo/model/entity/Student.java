package com.qorakol.ilm.ziyo.model.entity;

import com.qorakol.ilm.ziyo.constant.StudentStatus;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "tel")
    private String telNomer;

    @Column(name = "q_tel")
    private String qTelNomer;

    private String description;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "status")
    private StudentStatus studentStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auth_id")
    private AuthEntity authEntity;


}
