package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.model.dto.SubjectDto;
import com.qorakol.ilm.ziyo.model.entity.*;
import com.qorakol.ilm.ziyo.repository.*;
import com.qorakol.ilm.ziyo.service.interfaces.StaticService;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StaticServiceImpl implements StaticService {
    private final LanguageRepository languageRepository;
    private final SubjectsRepository subjectsRepository;
    private final MainImagesRepository mainImagesRepository;
    private final ImagesRepository imagesRepository;
    private final GroupsRepository groupsRepository;
    private final ActivationRepository activationRepository;
    private final AttendanceRepository attendanceRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;


    public StaticServiceImpl(LanguageRepository languageRepository, SubjectsRepository subjectsRepository, MainImagesRepository mainImagesRepository, ImagesRepository imagesRepository, GroupsRepository groupsRepository, ActivationRepository activationRepository, AttendanceRepository attendanceRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.languageRepository = languageRepository;
        this.subjectsRepository = subjectsRepository;
        this.mainImagesRepository = mainImagesRepository;
        this.imagesRepository = imagesRepository;
        this.groupsRepository = groupsRepository;
        this.activationRepository = activationRepository;
        this.attendanceRepository = attendanceRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Object getAllLang() {
        return languageRepository.findAll();
    }

    @Override
    public Object getAllSubjects() {
        return subjectsRepository.findAllByDeleteIsFalse();
    }

    @Override
    public void addSubject(SubjectDto subjectDto) {
        Subjects subjects = new Subjects();
        BeanUtils.copyProperties(subjectDto, subjects);
        subjectsRepository.save(subjects);
    }

    @Override
    public Object putSubject(Long id, SubjectDto subjectDto) {
        Subjects subject = subjectsRepository.findByIdAndDeleteIsFalse(id);
        if (subject !=null) {
            BeanUtils.copyProperties(subjectDto, subjectDto);
            subjectsRepository.save(subject);
            return "SUCCESS";
        }else return "ERROR";
    }

    @Override
    public Object deleteSubject(Long id) {
        Subjects subjects = subjectsRepository.findByIdAndDeleteIsFalse(id);
        subjects.setDelete(true);
        subjectsRepository.save(subjects);
        return true;
    }

    @Override
    public Object getGroup() {

        List<Object> list = new ArrayList<>();
        List<Groups> groupsList = groupsRepository.findAllByDeleteIsFalse();
        for(Groups groups: groupsList){
            Map<String, Object> map = new HashMap<>();
            map.put("group", groups);
            map.put("soni", activationRepository.findAllByGroupIdAndDeleteIsFalse(groups.getId()).size());
            map.put("language", languageRepository.findById(groups.getLanguageId()).get());
            map.put("subject", subjectsRepository.findById(groups.getSubjectId()).get());
            map.put("teacher", teacherRepository.findById(groups.getTeacherId()).get());
            list.add(map);
        }

        return "sdadawd";
    }

    @Override
    public Object getStudent(Long id) {
        Student student = studentRepository.findById(id).get();
        Activation activation = activationRepository.findByStudentIdAndDeleteIsFalse(id);
        Groups groups = groupsRepository.findById(activation.getGroupId()).get();
        List<Attendances> attendancesLIst = attendanceRepository.findAllByCountedIsTrueAndActivationId(activation.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("student", student);
        map.put("tolanmagan_darslari", attendancesLIst);
        map.put("xolati", activation);
        map.put("groups", groups);
        return map;
    }


    @Override
    public ResponseEntity images(Long id) throws MalformedURLException {
        Images images=imagesRepository.findById(id).get();
        Path path= Paths.get(images.getUploadPath());
        Resource resource= new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(images.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName="+resource.getFilename())
                .body(resource);
    }


    @Override
    public Object getMainImages() {
        Pageable pageable= PageRequest.of(0, 3);
        Page<MainImage> imagePage = mainImagesRepository.findAllByDeleteIsFalse(pageable);
        return imagePage.getContent();
    }


}
