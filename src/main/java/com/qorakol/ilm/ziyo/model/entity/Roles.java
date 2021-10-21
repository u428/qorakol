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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Priviliges> getPriviliges() {
        return priviliges;
    }

    public void setPriviliges(Set<Priviliges> priviliges) {
        this.priviliges = priviliges;
    }
}
