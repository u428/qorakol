package com.qorakol.ilm.ziyo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qorakol.ilm.ziyo.constant.StudentStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "tel")
    private String telNomer;

    @Column(name = "q_tel")
    private String qTelNomer;

    private String description;

    private boolean delete = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "auth_id")
    private AuthEntity authEntity;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudentStatus status;

//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "student_group_join",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id")
//    )
//    private Set<Groups> groupsSet;


}
