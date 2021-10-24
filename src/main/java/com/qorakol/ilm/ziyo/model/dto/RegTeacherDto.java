package com.qorakol.ilm.ziyo.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class RegTeacherDto {

    public String firstName;

    public String lastName;

    public String middleName;

    public String telNomer;

    public String tgLink;

    public String inLink;

    public String fLink;

    public String gmail;

    public String description;

    public String login;

    public String password;
}
