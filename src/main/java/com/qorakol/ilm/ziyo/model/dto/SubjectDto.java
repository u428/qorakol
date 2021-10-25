package com.qorakol.ilm.ziyo.model.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubjectDto {

    public String nameUz;

    public String nameRu;

    public String nameEn;

    public String description;

}
