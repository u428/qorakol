package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.repository.StudentRepository;
import com.qorakol.ilm.ziyo.service.interfaces.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final StudentRepository studentRepository;

    public TeacherServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



}
