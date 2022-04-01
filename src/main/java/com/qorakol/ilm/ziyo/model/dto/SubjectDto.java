package com.qorakol.ilm.ziyo.model.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class SubjectDto {

    public Long id;

    public String nameUz;

    public String nameRu;

    public String nameEn;

    public String description;

}
