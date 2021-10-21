package com.qorakol.ilm.ziyo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sys_roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private int level;

    @OneToOne(mappedBy = "roles")
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Set<Priviliges> priviliges;


}
