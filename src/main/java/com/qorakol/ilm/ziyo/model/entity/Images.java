package com.qorakol.ilm.ziyo.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "sys_images")
@Data
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String extention;

    @Column
    private Long fileSize;

    @JsonIgnore
    @Column
    private String contentType;

    @Column
    private String uploadPath;

    @Column
    private boolean delete = false;

}
