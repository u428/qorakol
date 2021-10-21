package com.qorakol.ilm.ziyo.model.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sys_auth_entity")
@Data
public class AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

}
