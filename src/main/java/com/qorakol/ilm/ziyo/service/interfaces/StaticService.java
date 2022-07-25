package com.qorakol.ilm.ziyo.service.interfaces;


import com.qorakol.ilm.ziyo.model.dto.SubjectDto;
import com.qorakol.ilm.ziyo.model.entity.Events;
import com.qorakol.ilm.ziyo.model.entity.Student;
import com.qorakol.ilm.ziyo.model.entity.Subjects;
import com.qorakol.ilm.ziyo.model.entity.Teacher;
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

    void putSubject(SubjectDto subjectDto) throws Exception;

    void deleteSubject(Long id) throws Exception;

    Map<String, Object> getGroup(int limit, int page) throws Exception;

    Map<String, Object> getTeachers(int limit, int page) throws Exception;

    Object getStudent(Long id) throws Exception;

    Map<String, Object> getStudentNew(int limit, int page);

    Object getStudentNotPayed();

    Map<String, Integer> getDashboard();

    Object lineGraph();

    Object landingTeacher();

    Object landingGroups();

    List<Events> landingEvent();

    Map<String, Object> getSingleTeacher(Long id) throws Exception;

    Subjects getViewSubject(Long id) throws Exception;

    Object getGroupOne(Long id) throws Exception;

    List<Teacher> getTeachersList(String name);

    Object viewTeacher(Long id) throws Exception;

    Object getStudentStatistic();
}
