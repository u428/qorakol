package com.qorakol.ilm.ziyo.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "groups")
@Data
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subjects subjects;

}
