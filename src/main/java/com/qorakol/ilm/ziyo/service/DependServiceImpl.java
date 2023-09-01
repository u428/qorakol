package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.constant.Common;
import com.qorakol.ilm.ziyo.constant.RoleContants;
import com.qorakol.ilm.ziyo.constant.StudentStatus;
import com.qorakol.ilm.ziyo.model.dto.RegStudentDto;
import com.qorakol.ilm.ziyo.model.dto.ResponseGroupList;
import com.qorakol.ilm.ziyo.model.dto.SToGroup;
import com.qorakol.ilm.ziyo.model.dto.StudentLogin;
import com.qorakol.ilm.ziyo.model.entity.*;
import com.qorakol.ilm.ziyo.repository.*;
import com.qorakol.ilm.ziyo.service.interfaces.DependService;
import com.qorakol.ilm.ziyo.utils.DateParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.*;

@Service
public class DependServiceImpl implements DependService {

    private final StudentRepository studentRepository;
    private final GroupsRepository groupsRepository;
    private final ActivationRepository activationRepository;
    private final ActivationDetailsRepository activationDetailsRepository;
    private final AttendanceRepository attendanceRepository;
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    private final SubjectsRepository subjectsRepository;

    public DependServiceImpl(StudentRepository studentRepository, GroupsRepository groupsRepository, ActivationRepository activationRepository, ActivationDetailsRepository activationDetailsRepository, AttendanceRepository attendanceRepository, AuthRepository authRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository,
                             SubjectsRepository subjectsRepository) {
        this.studentRepository = studentRepository;
        this.groupsRepository = groupsRepository;
        this.activationRepository = activationRepository;
        this.activationDetailsRepository = activationDetailsRepository;
        this.attendanceRepository = attendanceRepository;
        this.authRepository = authRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.subjectsRepository = subjectsRepository;
    }

    @Override
    public ResponseEntity getStudentList(int current, int pageSize) {
        if (current > 0) current--;
        Pageable pageable = PageRequest.of(current, pageSize);
        Page<Student> studentList = studentRepository.findByStatusAndDeleteIsFalse(StudentStatus.OQIYDI, pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("students", studentList.getContent());
        Map<String, Long> pagable = new HashMap<>();
        pagable.put("current", (long) ++current);
        pagable.put("pageSize", (long) pageSize);
        pagable.put("total", studentList.getTotalElements());
        map.put("pagination", pagable);
        return ResponseEntity.ok(map);
    }

    @Override
    public Object getStudentPayed(int current, int pageSize) {
        if (current > 0) current--;
        Pageable pageable = PageRequest.of(current, pageSize);
        Page<Student> studentList = studentRepository.findByStatusAndDeleteIsFalse(StudentStatus.OQIYDI, pageable);
        Map<String, Object> map = new HashMap<>();
        map.put("students", studentList.getContent());
        Map<String, Long> pagable = new HashMap<>();
        pagable.put("current", (long) ++current);
        pagable.put("pageSize", (long) pageSize);
        pagable.put("total", studentList.getTotalElements());
        map.put("pagination", pagable);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public Object createStudent(RegStudentDto regStudentDto) {
        Student student =  new Student();
        student.setFirstName(regStudentDto.getFirstName());
        student.setLastName(regStudentDto.getLastName());
        student.setTelNomer(regStudentDto.getTelNumber());
        student.setQTelNomer(regStudentDto.getQTelNumber());
        student.setDescription(regStudentDto.getDescription());
        student.setDateBirth(DateParser.TryParse(regStudentDto.getBirthDate(), Common.uzbekistanDateFormat));
        student.setCreatedAt(new Date());
        student.setStatus(StudentStatus.YANGI);
        List<Subjects> subjectsList = new ArrayList<>();
        for (int i =0; i < regStudentDto.getSubjectsId().size(); i++){
            Subjects subjects = subjectsRepository.findByIdAndDeleteIsFalse(regStudentDto.getSubjectsId().get(i));
            subjectsList.add(subjects);
        }
        student.setSubjects(subjectsList);
        Long result = studentRepository.save(student).getId();
        return result;
    }

    @Override
    public void addStudentLogin(StudentLogin studentLogin) {
        AuthEntity authEntity = authRepository.findByLogin(studentLogin.getLogin());
        if (authEntity !=null) throw new UsernameNotFoundException("login found in database");
        authEntity = new AuthEntity();
        authEntity.setLogin(studentLogin.getLogin());
        authEntity.setPassword(bCryptPasswordEncoder.encode(studentLogin.getPassword()));
        Roles roles = roleRepository.findByName(RoleContants.STUDENT);
        authEntity.setRolesId(roles.getId());
        Student student = studentRepository.findById(studentLogin.getStudentId()).orElse(null);
        if (student == null)throw new UsernameNotFoundException("student not found");
        authRepository.save(authEntity);
        student.setStatus(StudentStatus.OQIYDI);
        student.setAuthId(authEntity.getId());
        studentRepository.save(student);
    }

    @Override
    public void studentAddGroup(SToGroup sToGroup) throws Exception {
        Student student = studentRepository.findById(sToGroup.getStudentId()).get();
        if (student.getAuthId() == null) throw new UsernameNotFoundException("oldin login parol bering");
        Groups groups = groupsRepository.findById(sToGroup.getGroupId()).get();
        if (groups == null) throw new UsernameNotFoundException("id li group topilmadi");
        Activation activation = new Activation();
        activation.setActive(true);
        activation.setGroupId(sToGroup.getGroupId());
        activation.setStudentId(sToGroup.getStudentId());

        ActivationDetails activationDetails =  new ActivationDetails();
        activationDetails.setLessonPayed(0);
        activationDetails.setStatus(true);
        activationRepository.save(activation);
        activationDetails.setActivationId(activation.getId());
        activationDetailsRepository.save(activationDetails);
        if (student.getActivation() == null){
            student.setActivation(new ArrayList<>());
        }
        List<Activation> list = student.getActivation();
        list.add(activation);
        student.setActivation(list);
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) throws Exception {
        Student student = studentRepository.findByIdAndDeleteIsFalse(id);
        if (student == null) throw new Exception();
        student.setDelete(true);
        studentRepository.saveAndFlush(student);
    }

    @Override
    public Object getStudentSearchList(String name) {
        List<Student> studentList = studentRepository.findAllByStatusAndFirstNameContainingAndDeleteIsFalse(StudentStatus.YANGI, name);
        return studentList;
    }


    @Override
    public Object getGroupListSearch(String name) {
        List<Groups> groupsList = groupsRepository.findAllByNameContainingAndDeleteFalse(name);
        return groupsList;
    }

    @Override
    public Object getGroupListStudent(Long id) {

        List<ResponseGroupList> lists = new ArrayList<>();
        List<Activation> activationList = activationRepository.findAllByStudentIdAndDeleteIsFalse(id);

        List<Groups> groupsList = new ArrayList<>();

        for (Activation activation: activationList){
            ActivationDetails activationDetails = activationDetailsRepository.findByActivationIdAndDeleteIsFalse(activation.getId());
            ResponseGroupList responseGroupList = new ResponseGroupList();
            Groups groups = groupsRepository.findByIdAndDeleteIsFalse(activation.getGroupId());
            responseGroupList.setId(groups.getId());
            responseGroupList.setName(groups.getName());
            responseGroupList.setPrice(groups.getPrice());
            responseGroupList.setStatus(activationDetails.isStatus());
            groupsList.add(groups);

            long lessonAttendance = attendanceRepository.countAllByActivationIdAndDeleteIsFalse(activation.getId());
            responseGroupList.setAttendance(lessonAttendance);
            responseGroupList.setDeposite(activationDetails.getLessonPayed());
            lists.add(responseGroupList);
        }
        return lists;
    }


    @Override
    public Object getStudentListByGroup(Long id) {
        List<Student> studentList = new ArrayList<>();
        List<Activation> activationList = activationRepository.findAllByGroupIdAndDeleteIsFalse(id);
        for (Activation activation: activationList){
            Student student = studentRepository.findByIdAndDeleteIsFalse(activation.getStudentId());
            studentList.add(student);
        }
        return studentList;
    }
}
