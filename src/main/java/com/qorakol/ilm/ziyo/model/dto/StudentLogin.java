package com.qorakol.ilm.ziyo.model.dto;

import lombok.Data;

@Data
public class StudentLogin {

    public Long studentId;

    public String login;

    public String password;
}
