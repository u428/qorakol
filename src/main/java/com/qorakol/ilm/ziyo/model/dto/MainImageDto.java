package com.qorakol.ilm.ziyo.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class MainImageDto {

    private String name;

    private String descryption;

    public MultipartFile files;


}
