package com.qorakol.ilm.ziyo.service.interfaces;

import com.qorakol.ilm.ziyo.model.dto.*;
import com.qorakol.ilm.ziyo.model.entity.Roles;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.net.MalformedURLException;
import java.util.Map;

public interface AuthService extends UserDetailsService {

    boolean checkLogin(String login);



    Roles getRoles(String login);

    Long createStudent(RegStudentDto regStudentDto);

    Map<String, Object> getCurrentUser(String login);

    void studentAddGroup(SToGroup sToGroup);

    void addAdmin(AdminDto adminDto);

    void addRole();

    Object getAdmins();

    Object addStudentLogin(StudentLogin studentLogin);
}
