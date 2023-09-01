package com.qorakol.ilm.ziyo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseGroupList {

    private Long id;

    private String name;

    private double price;

    private boolean status;

    private int lessonPayed;

    private long deposite;

    private long attendance;

}
