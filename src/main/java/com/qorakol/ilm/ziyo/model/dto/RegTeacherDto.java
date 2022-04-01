package com.qorakol.ilm.ziyo.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class RegTeacherDto {

    public Long id;

    public String firstName;
    public String lastName;

    public String middleName;
    public String telNomer;

    public String tgLink;

    public String dateBirth;

    public String inLink;

    public String fLink;
    public String gmail;

    public String description;

    public String login;

    public int gender;

    public String password;

    public Long fileId;

    public List<Long> subjectIds;

    public List<Long> langIds;
}

