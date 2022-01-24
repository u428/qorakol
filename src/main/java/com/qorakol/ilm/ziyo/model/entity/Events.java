package com.qorakol.ilm.ziyo.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sys_events")
@Data
public class Events {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Temporal(TemporalType.DATE)
    @Column
    private Date time;

    @Temporal(TemporalType.DATE)
    @CreatedDate
    @Column
    @JsonIgnore
    private Date date;

    @JsonIgnore
    private boolean delete = false;

    @Column(name = "image_id")
    private Long ImagesId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    private Images images;

}
