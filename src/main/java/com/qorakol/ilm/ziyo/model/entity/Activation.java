package com.qorakol.ilm.ziyo.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activation")
@Data
public class Activation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "active")
    private boolean active = false;

    private boolean delete = false;

    @Column(name = "student_id")
    private Long studentId;

    @Temporal(TemporalType.DATE)
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "group_id")
    private Long groupId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private Groups groups;

    @OneToOne(mappedBy = "activation")
    private ActivationDetails activationDetails;
}

