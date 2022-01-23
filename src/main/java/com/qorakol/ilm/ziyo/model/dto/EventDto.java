package com.qorakol.ilm.ziyo.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class EventDto {

    public String title;

    public String description;

    public Date date;

    public MultipartFile files;

}
