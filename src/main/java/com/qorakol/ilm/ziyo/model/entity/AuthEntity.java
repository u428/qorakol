package com.qorakol.ilm.ziyo.model.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sys_auth_entity")
@Data
public class AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role_id", nullable = false)
    private Long rolesId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
    private Roles roles;

}
