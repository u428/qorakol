package com.qorakol.ilm.ziyo.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "sys_language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;


}
