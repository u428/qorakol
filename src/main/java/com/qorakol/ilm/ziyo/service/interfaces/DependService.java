package com.qorakol.ilm.ziyo.service.interfaces;

import com.qorakol.ilm.ziyo.model.dto.RegStudentDto;
import com.qorakol.ilm.ziyo.model.dto.SToGroup;
import com.qorakol.ilm.ziyo.model.dto.StudentLogin;
import org.springframework.http.ResponseEntity;

public interface DependService {

    Object createStudent(RegStudentDto regStudentDto);

    void addStudentLogin(StudentLogin studentLogin);

    public void studentAddGroup(SToGroup sToGroup) throws Exception;

    ResponseEntity getStudentList(int current, int pageSize);

    Object getStudentPayed(int current, int pageSize);

    void deleteStudent(Long id) throws Exception;

    Object getStudentSearchList(String name);

    Object getGroupListSearch(String name);
}
