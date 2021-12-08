package com.qorakol.ilm.ziyo.service.interfaces;


import com.qorakol.ilm.ziyo.model.dto.SubjectDto;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;

public interface StaticService {
    Object getAllLang();

    void addSubject(SubjectDto subjectDto);

    Object getMainImages();

    Object getAllSubjects();

    ResponseEntity images(Long id) throws MalformedURLException;

    Object putSubject(Long id, SubjectDto subjectDto);

    Object deleteSubject(Long id);

    Object getGroup();

    Object getTeachers(int limit, int page);

    Object getStudent(Long id);

    Object getStudentNew();

    Object getStudentPayed();

    Object getStudentNotPayed();
}
