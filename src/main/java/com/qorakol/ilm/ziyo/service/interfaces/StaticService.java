package com.qorakol.ilm.ziyo.service.interfaces;


import com.qorakol.ilm.ziyo.model.dto.SubjectDto;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public interface StaticService {
    Object getAllLang() throws Exception;

    void addSubject(SubjectDto subjectDto) throws Exception;

    Object getMainImages() throws Exception;

    Object getAllSubjects() throws Exception;

    ResponseEntity images(Long id) throws MalformedURLException;

    void putSubject(Long id, SubjectDto subjectDto) throws Exception;

    void deleteSubject(Long id) throws Exception;

    Object getGroup() throws Exception;

    List<Map> getTeachers(int limit, int page) throws Exception;

    Map<String, Object> getStudent(Long id) throws Exception;

    Object getStudentNew();

    Object getStudentPayed();

    Object getStudentNotPayed();
}
