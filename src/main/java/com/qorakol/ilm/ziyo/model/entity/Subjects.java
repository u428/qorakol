package com.qorakol.ilm.ziyo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sys_subjects")
@Data
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "subjects")
    private List<Groups> groups;



}
