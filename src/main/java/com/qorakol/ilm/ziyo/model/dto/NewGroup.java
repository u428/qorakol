package com.qorakol.ilm.ziyo.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NewGroup {

    public String name;

    public Date begin;

    public Date finish;

    public Long teacherId;

    public Long subjectId;

    public Long languageId;

}
