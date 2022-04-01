package com.qorakol.ilm.ziyo.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Data
public class NewGroup {

    public Long id;

    public String name;

    public String begin;

    public String finish;

    public String description;

    public double price;

    public Long teacherId;

    public Long subjectId;

    public Long languageId;

    public Long filesId;

}
