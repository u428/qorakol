package com.qorakol.ilm.ziyo.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RegTeacherDto {
    @NotEmpty
    public String firstName;
    @NotEmpty
    public String lastName;

    public String middleName;
    @NotEmpty
    public String telNomer;

    public String tgLink;

    public String inLink;

    public String fLink;
    @NotEmpty
    public String gmail;

    public String description;
    @NotEmpty
    public String login;
    @NotEmpty
    public String password;

    public MultipartFile files;

    public List<Long> subjectIds;

    public List<Long> langIds;
}

