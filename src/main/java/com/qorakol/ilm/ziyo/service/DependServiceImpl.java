package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.constant.Common;
import com.qorakol.ilm.ziyo.constant.RoleContants;
import com.qorakol.ilm.ziyo.constant.StudentStatus;
import com.qorakol.ilm.ziyo.model.dto.RegStudentDto;
import com.qorakol.ilm.ziyo.model.dto.SToGroup;
import com.qorakol.ilm.ziyo.model.dto.StudentLogin;
import com.qorakol.ilm.ziyo.model.entity.*;
import com.qorakol.ilm.ziyo.repository.*;
import com.qorakol.ilm.ziyo.service.interfaces.DependService;
import com.qorakol.ilm.ziyo.utils.DateParser;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DependServiceImpl implements DependService {

    private final StudentRepository studentRepository;
    private final GroupsRepository groupsRepository;
    private final ActivationRepository activationRepository;
    private final ActivationDetailsRepository activationDetailsRepository;
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    public DependServiceImpl(StudentRepository studentRepository, GroupsRepository groupsRepository, ActivationRepository activationRepository, ActivationDetailsRepository activationDetailsRepository, AuthRepository authRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.groupsRepository = groupsRepository;
        this.activationRepository = activationRepository;
        this.activationDetailsRepository = activationDetailsRepository;
        this.authRepository = authRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
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
        student.setStatus(StudentStatus.YANGI);
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
}
