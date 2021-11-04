package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.model.dto.CheckStudents;
import com.qorakol.ilm.ziyo.model.entity.Activation;
import com.qorakol.ilm.ziyo.model.entity.AuthEntity;
import com.qorakol.ilm.ziyo.model.entity.Groups;
import com.qorakol.ilm.ziyo.model.entity.Teacher;
import com.qorakol.ilm.ziyo.repository.*;
import com.qorakol.ilm.ziyo.security.CurrentUser;
import com.qorakol.ilm.ziyo.service.interfaces.TeacherService;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final StudentRepository studentRepository;
    private final AuthRepository authRepository;
    private final TeacherRepository teacherRepository;
    private final GroupsRepository groupsRepository;
    private final ActivationRepository activationRepository;

    public TeacherServiceImpl(StudentRepository studentRepository, AuthRepository authRepository, TeacherRepository teacherRepository, GroupsRepository groupsRepository, ActivationRepository activationRepository) {
        this.studentRepository = studentRepository;
        this.authRepository = authRepository;
        this.teacherRepository = teacherRepository;
        this.groupsRepository = groupsRepository;
        this.activationRepository = activationRepository;
    }


    @Override
    public Object getGroups(String login) {
        AuthEntity authEntity = authRepository.findByLogin(login);
        Teacher teacher = teacherRepository.findByAuthEntity(authEntity);
        return groupsRepository.findAllByTeacherIdAndDeleteIsFalse(teacher.getId());
    }

    @Override
    public Object getGroupStudents(String login, Long id) {
        List<Activation> activation = activationRepository.findAllByGroupIdAndDeleteIsFalse(id);
        return activation;
    }

    @Override
    public Object checkStudent(String login, CheckStudents checkStudents) {
        AuthEntity authEntity = authRepository.findByLogin(login);
        Teacher teacher = teacherRepository.findByAuthEntity(authEntity);

        Groups groups = groupsRepository.findById(checkStudents.getGroupId()).get();

        for ()
        Activation activation = activationRepository.findByStudentIdAndGroupId()


        return null;
    }


}
