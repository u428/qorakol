package com.qorakol.ilm.ziyo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qorakol.ilm.ziyo.constant.RoleContants;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sys_roles")
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleContants name;

    private int level;

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roles")
//    private Set<Priviliges> priviliges;

}
