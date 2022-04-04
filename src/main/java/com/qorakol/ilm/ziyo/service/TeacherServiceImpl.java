package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.model.dto.CheckStudents;
import com.qorakol.ilm.ziyo.model.dto.GroupsTeacher;
import com.qorakol.ilm.ziyo.model.entity.*;
import com.qorakol.ilm.ziyo.repository.*;
import com.qorakol.ilm.ziyo.service.interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final StudentRepository studentRepository;
    private final AuthRepository authRepository;
    private final TeacherRepository teacherRepository;
    private final GroupsRepository groupsRepository;
    private final ActivationRepository activationRepository;
    private final ActivationDetailsRepository activationDetailsRepository;
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public TeacherServiceImpl(StudentRepository studentRepository, AuthRepository authRepository, TeacherRepository teacherRepository, GroupsRepository groupsRepository, ActivationRepository activationRepository, ActivationDetailsRepository activationDetailsRepository, AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.authRepository = authRepository;
        this.teacherRepository = teacherRepository;
        this.groupsRepository = groupsRepository;
        this.activationRepository = activationRepository;
        this.activationDetailsRepository = activationDetailsRepository;
        this.attendanceRepository = attendanceRepository;
    }


//    @Override
//    public Object getGroups(String login) {
//        AuthEntity authEntity = authRepository.findByLoginAndDeleteIsFalse(login);
//        Teacher teacher = teacherRepository.findByAuthEntity(authEntity);
//        return groupsRepository.findAllByTeacherIdAndDeleteIsFalse(teacher.getId());
//    }

    @Override
    public Object getGroupStudents(String login, Long id) {
        List<Activation> activation = activationRepository.findAllByGroupIdAndDeleteIsFalse(id);
        return activation;
    }

    @Override
    public void checkStudent(String login, CheckStudents checkStudents) throws Exception {
        AuthEntity authEntity = authRepository.findByLoginAndDeleteIsFalse(login);
        Teacher teacher = teacherRepository.findByAuthEntity(authEntity);
        Groups groups = groupsRepository.findById(checkStudents.getGroupId()).get();
        if (groups.getTeacherId() != teacher.getId()) throw new Exception();
        for (Long id: checkStudents.getIds()){
            Activation activation = activationRepository.findByStudentIdAndGroupId(id, groups.getId());
            ActivationDetails activationDetails = activationDetailsRepository.findByActivationIdAndDeleteIsFalse(activation.getId());
            activationDetails.lessonPayedDecrease();
            if (activationDetails.getLessonPayed() < 0){
                activationDetails.setStatus(false);
            }else {
                activationDetails.setStatus(true);
            }
            activationDetailsRepository.save(activationDetails);

            Attendances attendances = new Attendances();
            attendances.setActivationId(activation.getId());
            attendances.setTime(new Date());
            attendanceRepository.save(attendances);
        }
    }

    @Override
    public Object getGroups(String login) throws Exception {
        AuthEntity authEntity = authRepository.findByLogin(login);
        if (authEntity == null) throw new Exception();
        Teacher teacher = teacherRepository.findByAuthIdAndDeleteIsFalse(authEntity.getId());
        if (teacher == null) throw new Exception();
        List<Groups> groupList = groupsRepository.findAllByTeacherId(teacher.getId());
        List<GroupsTeacher> groupsTeacherList = new ArrayList<>();
        for (Groups groups: groupList){
            GroupsTeacher groupsTeacher = new GroupsTeacher();
            groupsTeacher.setId(groups.getId());
            groupsTeacher.setName(groups.getName());
            groupsTeacher.setPrice(groups.getPrice());

            groupsTeacher.setStudents(15);
            groupsTeacherList.add(groupsTeacher);
        }
        return groupsTeacherList;
    }
}
