package com.qorakol.ilm.ziyo.service.interfaces;


import com.qorakol.ilm.ziyo.model.dto.SubjectDto;
import com.qorakol.ilm.ziyo.model.entity.Events;
import com.qorakol.ilm.ziyo.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

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

    Object getGroup(int limit, int page) throws Exception;

    Map<String, Object> getTeachers(int limit, int page) throws Exception;

    Map<String, Object> getStudent(Long id) throws Exception;

    Page<Student> getStudentNew(int limit, int page);

    Object getStudentPayed(int limit, int page);

    Object getStudentNotPayed();

    Map<String, Integer> getDashboard();

    Object lineGraph();

    Object landingTeacher();

    Object landingGroups();

    List<Events> landingEvent();

    Map<String, Object> getSingleTeacher(Long id) throws Exception;
}
