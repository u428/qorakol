package com.qorakol.ilm.ziyo.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Column;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RegTeacherDto {

    private String firstName;

    private String lastName;

    private String middleName;

    private String telNomer;

    private String tgLink;

    private String inLink;

    private String fLink;

    private String gmail;

    private String description;

    private String login;

    private String password;
}
