package com.qorakol.ilm.ziyo.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qorakol.ilm.ziyo.constant.StudentStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
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

    @Temporal(TemporalType.DATE)
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    private boolean delete = false;

    @Column(name = "auth_id")
    private Long authId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone="Asia/Tashkent")
    @Temporal(TemporalType.DATE)
    @Column(name = "date_birth")
    private Date dateBirth;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "auth_id", insertable = false, updatable = false)
    private AuthEntity authEntity;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Activation> activation;





}
