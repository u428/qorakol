package com.qorakol.ilm.ziyo.service.interfaces;

import com.qorakol.ilm.ziyo.model.dto.*;
import com.qorakol.ilm.ziyo.model.entity.Roles;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface AuthService extends UserDetailsService {

    boolean checkLogin(String login);



    Roles getRoles(String login) throws Exception;

    Long createStudent(RegStudentDto regStudentDto);

    Map<String, Object> getCurrentUser(String login);

    void studentAddGroup(SToGroup sToGroup) throws Exception;

    void addAdmin(AdminDto adminDto) throws Exception;

    void addRole();

    Object getAdmins();

    void addStudentLogin(StudentLogin studentLogin) throws Exception;
}
