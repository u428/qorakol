package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.model.dto.CheckStudents;
import com.qorakol.ilm.ziyo.model.entity.*;
import com.qorakol.ilm.ziyo.repository.*;
import com.qorakol.ilm.ziyo.service.interfaces.TeacherService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final StudentRepository studentRepository;
    private final AuthRepository authRepository;
    private final TeacherRepository teacherRepository;
    private final GroupsRepository groupsRepository;
    private final ActivationRepository activationRepository;
    private final AttendanceRepository attendanceRepository;

    public TeacherServiceImpl(StudentRepository studentRepository, AuthRepository authRepository, TeacherRepository teacherRepository, GroupsRepository groupsRepository, ActivationRepository activationRepository, AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.authRepository = authRepository;
        this.teacherRepository = teacherRepository;
        this.groupsRepository = groupsRepository;
        this.activationRepository = activationRepository;
        this.attendanceRepository = attendanceRepository;
    }


    @Override
    public Object getGroups(String login) {
        AuthEntity authEntity = authRepository.findByLoginAndDeleteIsFalse(login);
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
        AuthEntity authEntity = authRepository.findByLoginAndDeleteIsFalse(login);
        Teacher teacher = teacherRepository.findByAuthEntity(authEntity);
        Groups groups = groupsRepository.findById(checkStudents.getGroupId()).get();
        for (Long id: checkStudents.getIds()){
            Activation activation = activationRepository.findByStudentIdAndGroupId(id, groups.getId());
            Attendances attendances = new Attendances();
            attendances.setActivationId(activation.getId());
            attendances.setTime(new Date());
            attendanceRepository.save(attendances);
        }
        return "SUCCESS";
    }

}
