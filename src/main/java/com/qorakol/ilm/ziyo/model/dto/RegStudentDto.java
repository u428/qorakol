package com.qorakol.ilm.ziyo.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
public class RegStudentDto {


    public String firstName;

    public String lastName;

    public String birthDate;

    public String telNumber;

    public String qTelNumber;

    public String description;

    public List<Long> subjectsId;

}
