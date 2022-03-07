package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.constant.StudentStatus;
import com.qorakol.ilm.ziyo.model.dto.SubjectDto;
import com.qorakol.ilm.ziyo.model.entity.*;
import com.qorakol.ilm.ziyo.repository.*;
import com.qorakol.ilm.ziyo.service.interfaces.StaticService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;


@Service
public class StaticServiceImpl implements StaticService {
    private final LanguageRepository languageRepository;
    private final SubjectsRepository subjectsRepository;
    private final MainImagesRepository mainImagesRepository;
    private final ImagesRepository imagesRepository;
    private final GroupsRepository groupsRepository;
    private final ActivationRepository activationRepository;
    private final ActivationDetailsRepository activationDetailsRepository;
    private final AttendanceRepository attendanceRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final EventRepository eventRepository;


    @Autowired
    public StaticServiceImpl(LanguageRepository languageRepository, SubjectsRepository subjectsRepository, MainImagesRepository mainImagesRepository, ImagesRepository imagesRepository, GroupsRepository groupsRepository, ActivationRepository activationRepository, ActivationDetailsRepository activationDetailsRepository, AttendanceRepository attendanceRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, EventRepository eventRepository) {
        this.languageRepository = languageRepository;
        this.subjectsRepository = subjectsRepository;
        this.mainImagesRepository = mainImagesRepository;
        this.imagesRepository = imagesRepository;
        this.groupsRepository = groupsRepository;
        this.activationRepository = activationRepository;
        this.activationDetailsRepository = activationDetailsRepository;
        this.attendanceRepository = attendanceRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Object getAllLang() throws Exception{
        return languageRepository.findAll();
    }

    @Override
    public Object getAllSubjects() throws Exception {
        return subjectsRepository.findAllByDeleteIsFalse();
    }

    @Override
    public void addSubject(SubjectDto subjectDto) throws Exception {
        Subjects subjects = new Subjects();
        BeanUtils.copyProperties(subjectDto, subjects);
        subjectsRepository.save(subjects);
    }

    @Override
    public void putSubject(Long id, SubjectDto subjectDto)throws Exception {
        Subjects subject = subjectsRepository.findByIdAndDeleteIsFalse(id);
        if (subject !=null) {
            BeanUtils.copyProperties(subjectDto, subjectDto);
            subjectsRepository.save(subject);
        }else{
            throw new Exception();
        }
    }

    @Override
    public void deleteSubject(Long id) throws Exception {
        Subjects subjects = subjectsRepository.findByIdAndDeleteIsFalse(id);
        subjects.setDelete(true);
        subjectsRepository.save(subjects);
    }

    @Override
    public Object getGroup(int limit, int page) throws Exception {
        if (page > 0) page--;
        Pageable pageable = PageRequest.of(page, limit);
        List<Map<String, Object>> list = new ArrayList<>();
        Page<Groups> groupsList = groupsRepository.findAllByDeleteIsFalse(pageable);
        for(Groups groups: groupsList.getContent()){
            Map<String, Object> map = new HashMap<>();
            map.put("group", groups);
            map.put("soni", activationRepository.findAllByGroupIdAndDeleteIsFalse(groups.getId()).size());
            map.put("language", languageRepository.findById(groups.getLanguageId()).get());
            map.put("subject", subjectsRepository.findById(groups.getSubjectId()).get());
            map.put("teacher", teacherRepository.findById(groups.getTeacherId()).get());
            list.add(map);
        }
        Map<String, Object> map2 = new HashMap<>();
        map2.put("total_pages", groupsList.getTotalPages());
        map2.put("total_elements", groupsList.getTotalElements());

        return "sdadawd";
    }

    @Override
    public Map<String, Object> getStudent(Long id) throws Exception {
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
    public Page<Student> getStudentNew(int limit, int page) {
        if (page > 0) page--;
        Pageable pageable = PageRequest.of(page, limit);
        Page<Student> studentList = studentRepository.findByStatusAndDeleteIsFalse(StudentStatus.YANGI, pageable);
        return studentList;
    }

    @Override
    public Object getStudentPayed(int limit, int page) {
        if (page > 0) page--;
        Pageable pageable = PageRequest.of(page, limit);

        activationDetailsRepository.countByStatusAndDeleteIsFalse(true);
        return null;
    }

    @Override
    public Object getStudentNotPayed() {
        return null;
    }

    @Override
    public Map<String, Integer> getDashboard() {
        Map<String, Integer> returnObject = new HashMap<>();
        returnObject.put("new", Math.toIntExact(studentRepository.countByStatusAndDeleteIsFalse(StudentStatus.YANGI)));
        returnObject.put("all", Math.toIntExact(studentRepository.countByDeleteIsFalse()));
        returnObject.put("payed", Math.toIntExact(activationDetailsRepository.countByStatusAndDeleteIsFalse(true)));
        returnObject.put("not_payed", Math.toIntExact(activationDetailsRepository.countByStatusAndDeleteIsFalse(false)));
        return returnObject;
    }

    @Override
    public Object lineGraph() {
        return null;
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
    public Object getMainImages() throws Exception {
        Pageable pageable= PageRequest.of(0, 2);
        Page<MainImage> imagePage = mainImagesRepository.findAllByDeleteIsFalse(pageable);
        return imagePage.getContent();
    }


    @Override
    public Map<String, Object> getTeachers(int limit, int page) throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        if (page < 0) throw new Exception("bunday page bolmaydi");
        if (page > 0) page--;
        Pageable pageable = PageRequest.of(page, limit);
        Page<Teacher> list = teacherRepository.findAllByDeleteIsFalse(pageable);
        List<Map> returns = new ArrayList<>();
        for (Teacher teacher: list.getContent()){
            Map<String, Object> map = new HashMap<>();
            List<Groups> groups = groupsRepository.findAllByTeacherIdAndDeleteIsFalse(teacher.getId());
            map.put("groups", groups.size());
            map.put("teacher", teacher);
            returns.add(map);
        }
        Map<String, Object> paginations = new HashMap<>();
        paginations.put("total", list.getTotalElements());
        paginations.put("total_pages", list.getTotalPages());
        paginations.put("current", page+1);
        paginations.put("pageSize", limit);
        returnMap.put("pagination", paginations);
        returnMap.put("returns", returns);
        return returnMap;
    }

    @Override
    public Object landingTeacher() {
        List<Teacher> teacherList = teacherRepository.findAllByDeleteIsFalse();
        return teacherList;
    }

    @Override
    public Object landingGroups() {
        Page<Groups> groupsList;
        for (;;) {
            Pageable pageable = PageRequest.of(new Random(3).nextInt(), 4);
            groupsList = groupsRepository.findAllByDeleteIsFalse(pageable);
            if (groupsList.getContent().size() != 0){
                break;
            }
        }
        return groupsList;
    }

    @Override
    public List<Events> landingEvent() {
        List<Events> eventsList = eventRepository.findAllByDeleteIsFalse();
        return eventsList;
    }

    @Override
    public Map<String, Object> getSingleTeacher(Long id) throws Exception {
        Teacher teacher = teacherRepository.findByIdAndDeleteIsFalse(id).orElse(null);
        if (teacher == null) throw new Exception();

        List<Groups> groupsList = groupsRepository.findAllByTeacherIdAndDeleteIsFalse(teacher.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("teacher", teacher);
        map.put("groups", groupsList);

        return map;
    }                                                                                                    



}
