package com.qorakol.ilm.ziyo.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sys_auth_entity")
@Data
public class AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    @JsonIgnore
    private String login;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @JsonIgnore
    private boolean delete = false;

    @Column(name = "role_id", nullable = false)
    @JsonIgnore
    private Long rolesId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Roles roles;

}
