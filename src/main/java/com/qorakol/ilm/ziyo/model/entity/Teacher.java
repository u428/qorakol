package com.qorakol.ilm.ziyo.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "tel_nomer")
    private String telNomer;

    @Column(name = "tg_link")
    private String tgLink;

    @Column(name = "in_link")
    private String inLink;

    @Column(name = "fe_link")
    private String fLink;

    @Column(name = "gmail")
    private String gmail;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    private Roles roles;

    @OneToOne()
    @JoinColumn(name = "photo_id")
    private Images images;



}
