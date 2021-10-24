package com.qorakol.ilm.ziyo.model.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "student_attendance")
@Data
public class Attendances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "group_id")
    private Long groupId;

    @OneToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @OneToOne
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private Groups groups;

}
