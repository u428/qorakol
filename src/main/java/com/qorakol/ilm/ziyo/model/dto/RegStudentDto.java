package com.qorakol.ilm.ziyo.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class RegStudentDto {

    @NotEmpty
    public String firstName;
    @NotEmpty
    public String lastName;
    @NotEmpty
    public String telNomer;
    @NotEmpty
    public String qTelNomer;
    @NotEmpty
    public String description;

    public Long studentId;


}
