package com.qorakol.ilm.ziyo.model.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sys_language")
@Data
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
