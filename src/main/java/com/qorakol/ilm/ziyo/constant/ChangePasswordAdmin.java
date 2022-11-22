package com.qorakol.ilm.ziyo.constant;

import lombok.Data;

@Data
public class ChangePasswordAdmin {

    public String login;
    public String password;
    public Long id;
    public Long roleId;
}
