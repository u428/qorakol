package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.constant.Common;
import com.qorakol.ilm.ziyo.constant.PaymentStatus;
import com.qorakol.ilm.ziyo.constant.RoleContants;
import com.qorakol.ilm.ziyo.model.dto.*;
import com.qorakol.ilm.ziyo.model.entity.*;
import com.qorakol.ilm.ziyo.repository.*;
import com.qorakol.ilm.ziyo.service.interfaces.AdminService;
import com.qorakol.ilm.ziyo.utils.DateParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {


    private final GroupsRepository groupsRepository;
    private final LanguageRepository languageRepository;
    private final ImagesRepository imagesRepository;
    private final MainImagesRepository mainImagesRepository;
    private final PaymentRepository paymentRepository;
    private final AttendanceRepository attendanceRepository;
    private final ActivationRepository activationRepository;
    private final ActivationDetailsRepository activationDetailsRepository;
    private final TeacherRepository teacherRepository;
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    private final SubjectsRepository subjectsRepository;

    private Path fileStoragePath;

    @Autowired
    public AdminServiceImpl(GroupsRepository groupsRepository, LanguageRepository languageRepository, ImagesRepository imagesRepository, MainImagesRepository mainImagesRepository, PaymentRepository paymentRepository, AttendanceRepository attendanceRepository, ActivationRepository activationRepository, ActivationDetailsRepository activationDetailsRepository, TeacherRepository teacherRepository, AuthRepository authRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, SubjectsRepository subjectsRepository) {
        this.groupsRepository = groupsRepository;
        this.languageRepository = languageRepository;
        this.imagesRepository = imagesRepository;
        this.mainImagesRepository = mainImagesRepository;
        this.paymentRepository = paymentRepository;
        this.attendanceRepository = attendanceRepository;
        this.activationRepository = activationRepository;
        this.activationDetailsRepository = activationDetailsRepository;
        this.teacherRepository = teacherRepository;
        this.authRepository = authRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.subjectsRepository = subjectsRepository;
        fileStoragePath = Paths.get("java/java_img").toAbsolutePath().normalize();
        if (!Files.exists(fileStoragePath)){
            try {
                Files.createDirectories(fileStoragePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void createTeacher(RegTeacherDto regTeacherDto) throws Exception {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(regTeacherDto, teacher);
        AuthEntity authEntity=new AuthEntity();
        authEntity.setLogin(regTeacherDto.getLogin());
        authEntity.setPassword(bCryptPasswordEncoder.encode(regTeacherDto.getPassword()));
        Roles role = roleRepository.findByName(RoleContants.TEACHER);
        authEntity.setRolesId(role.getId());
        List<Subjects> subjectsList = subjectsRepository.findAllByIdIn(regTeacherDto.getSubjectIds());
        List<Language> languageList = languageRepository.findAllByIdIn(regTeacherDto.getLangIds());
        teacher.setLanguages(languageList);
        teacher.setSubjects(subjectsList);
        teacher.setDateBirth(DateParser.TryParse(regTeacherDto.getDateBirth(), Common.uzbekistanDateFormat));
        Images images = imagesRepository.findById(regTeacherDto.getFileId()).orElse(null);
        if (images == null) throw new Exception();
            teacher.setImagesId(images.getId());
            authRepository.save(authEntity);
            teacher.setAuthId(authEntity.getId());
            teacherRepository.save(teacher);
    }

    @Override
    public void changeTeacher(RegTeacherDto regTeacherDto) throws Exception {
        Teacher teacher = teacherRepository.findByIdAndDeleteIsFalse(regTeacherDto.getId()).orElse(null);
        if (teacher == null ) throw new Exception();
        BeanUtils.copyProperties(regTeacherDto, teacher);
        List<Subjects> subjectsList = subjectsRepository.findAllByIdIn(regTeacherDto.getSubjectIds());
        List<Language> languageList = languageRepository.findAllByIdIn(regTeacherDto.getLangIds());
        teacher.setLanguages(languageList);
        teacher.setSubjects(subjectsList);
        teacher.setImagesId(regTeacherDto.getFileId());
        teacherRepository.saveAndFlush(teacher);
    }

    @Override
    public void changeGroup(NewGroup newGroup) throws IOException {
        Groups groups = groupsRepository.findById(newGroup.getId()).get();
        BeanUtils.copyProperties(newGroup, groups);
        groups.setImagesId(newGroup.getFilesId());
        groupsRepository.save(groups);
    }

    @Override
    public void removeStudentFromGroup(Long studentId, Long groupId) throws Exception {
        Activation activation = activationRepository.findByStudentIdAndGroupIdAndDeleteIsFalse(studentId, groupId);
        if (activation == null) throw new Exception();
        ActivationDetails activationDetails = activationDetailsRepository.findByActivationIdAndDeleteIsFalse(activation.getId());
        activation.setActive(false);
        activationDetails.setStatus(false);
        activationDetailsRepository.save(activationDetails);
        activationRepository.save(activation);
    }

    @Override
    public void addEvent(EventDto eventDto) throws Exception {

    }

    @Override
    public void changeEvent(EventDto eventDto, long id) throws Exception {

    }

    @Override
    public void deleteEvent(long id) throws Exception {

    }

    @Override
    public void deleteGroup(Long id) throws Exception  {
        Groups groups = groupsRepository.findById(id).orElse(null);
        if (groups == null) throw new Exception("ERROR");
        groups.setDelete(true);
        groupsRepository.saveAndFlush(groups);
    }

    @Override
    public void deleteTeacher(Long id) throws Exception {
        Teacher teacher = teacherRepository.findByIdAndDeleteIsFalse(id).orElse(null);
        if (teacher == null) throw new Exception();
        teacher.setDelete(true);
        teacherRepository.save(teacher);
    }

    @Override
    public void addMainImage(MainImageDto mainImageDto) throws IOException {
        MainImage mainImage = new MainImage();
        mainImage.setDescription(mainImageDto.getDescryption());
        mainImage.setName(mainImageDto.getName());
            MultipartFile multipartFile = mainImageDto.getFiles();
            Images images = new Images();
            images.setContentType(multipartFile.getContentType());
            images.setName(multipartFile.getOriginalFilename());
            images.setFileSize(multipartFile.getSize());
            imagesRepository.save(images);
            String AA = multipartFile.getOriginalFilename();
            String fileName = String.valueOf(images.getId()) + AA.substring(AA.length() - 4, AA.length());
            images.setExtension(AA.substring(AA.length() - 4));
            System.out.println(fileStoragePath);
            Path filePath = Paths.get(fileStoragePath + "//" + fileName);
            images.setUploadPath(String.valueOf(filePath));
            Files.createDirectories(fileStoragePath);
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            imagesRepository.save(images);
            mainImage.setImagesId(images.getId());
            mainImagesRepository.save(mainImage);
    }

    @Override
    public void save(NewGroup newGroup) throws IOException {
        Groups groups = new Groups();
        BeanUtils.copyProperties(newGroup, groups);
        groups.setName(newGroup.getName());
        groups.setLanguageId(newGroup.getLanguageId());
        groups.setDescription(newGroup.getDescription());
        groups.setSubjectId(newGroup.getSubjectId());
        groups.setBegin(newGroup.getBegin());
        groups.setFinish(newGroup.getFinish());
        groups.setTeacherId(newGroup.getTeacherId());
        groups.setPrice(newGroup.getPrice());

        groups.setImagesId(newGroup.getFilesId());
        groupsRepository.save(groups);
    }

    @Override
    public void putImage(MainImageDto mainImageDto) throws IOException {
        if (mainImageDto.getId()!= null){
            MainImage mainImage = mainImagesRepository.findById(mainImageDto.getId()).get();
            BeanUtils.copyProperties(mainImageDto, mainImage);
            if (mainImageDto.getFiles()!=null){
                    addImage(mainImageDto.getFiles(), mainImage.getImagesId());
            }
            mainImagesRepository.save(mainImage);
        }else{
            throw new IOException();
        }
    }

    @Override
    public void paying(PaymentDto paymentDto) throws Exception {
        Payments payments=new Payments();
        Activation activation = activationRepository.findByStudentIdAndGroupIdAndDeleteIsFalse(paymentDto.getStudentId(), paymentDto.getGroupId());
        Groups group = groupsRepository.findById(paymentDto.getGroupId()).orElse(null);

        if (group == null) throw new Exception();
        double summ = paymentDto.getSumma()*12/group.getPrice();
        ActivationDetails activationDetails;
        activationDetails = activationDetailsRepository.findByActivationIdAndDeleteIsFalse(activation.getId());
        if (activationDetails == null){
            activationDetails = new ActivationDetails();
            activationDetails.setActivationId(activation.getId());
        }
        int peyd = activationDetails.getLessonPayed();
        peyd += summ;

        if (peyd < 0) {
            activationDetails.setStatus(false);
        }else{
            activationDetails.setStatus(true);
        }
        activationDetails.setLessonPayed(peyd);
        activationDetailsRepository.save(activationDetails);
        payments.setDarsSoati(summ);
        payments.setQolganDarsi(peyd);
        payments.setStudentId(paymentDto.getStudentId());
        payments.setGroupId(paymentDto.getGroupId());
        payments.setTime(new Date());
        payments.setSumma(paymentDto.getSumma());
        payments.setPaymentStatus(PaymentStatus.ALLOWED);
        payments.setActivationDetailsId(activationDetails.getId());
        paymentRepository.save(payments);
    }

    @Override
    public void changePayment(PaymentDto paymentDto) throws Exception {

        Payments payment =  paymentRepository.findById(paymentDto.getId()).orElse(null);
        if (payment == null) throw new Exception();
        payment.setPaymentStatus(PaymentStatus.CHANGED);
        Payments payments = new Payments();
        ActivationDetails activationDetails = activationDetailsRepository.findById(payment.getActivationDetailsId()).orElse(null);
        if (activationDetails == null) throw new Exception();
        Groups groups = groupsRepository.findById(payment.getGroupId()).orElse(null);
        Groups groups1 = groupsRepository.findById(paymentDto.getGroupId()).orElse(null);
        double summ = payment.getSumma() / groups.getPrice();
        double sum = paymentDto.getSumma() / groups1.getPrice();

        double lessonPayed = activationDetails.getLessonPayed();
        lessonPayed -= summ;
        lessonPayed +=sum;
        activationDetails.setLessonPayed((int) lessonPayed);
        if (lessonPayed < 0){
            activationDetails.setStatus(false);
        }else activationDetails.setStatus(true);
        payments.setPaymentStatus(PaymentStatus.ALLOWED);
        payments.setStudentId(payment.getStudentId());
        payments.setTime(new Date());
        payments.setSumma(paymentDto.getSumma());
        payments.setGroupId(paymentDto.getGroupId());
        payments.setQolganDarsi(lessonPayed);
        payments.setDarsSoati(sum);
        paymentRepository.save(payments);
        paymentRepository.save(payment);
        activationDetailsRepository.save(activationDetails);
    }

    @Override
    public Object deleteImage(Long id) {
        Images images = imagesRepository.findById(id).orElse(null);

        return null;
    }


    private void addImage(MultipartFile multipartFile, Long id) throws IOException {
        String AA=multipartFile.getOriginalFilename();
        Images image = imagesRepository.findById(id).get();
        image.setFileSize(multipartFile.getSize());
        image.setName(multipartFile.getOriginalFilename());
        image.setContentType(multipartFile.getContentType());

        Path path=Paths.get(image.getUploadPath());
        Files.deleteIfExists(path);

        String fileName = String.valueOf(image.getId())+AA.substring(AA.length()-4, AA.length());
        Path filePath = Paths.get(fileStoragePath + "//" + fileName);
        System.out.println(filePath);
        image.setUploadPath(String.valueOf(filePath));
        image.setExtension(AA.substring(AA.length()-4));
        imagesRepository.save(image);
            System.out.println(filePath);
            System.out.println(path);

            if(Files.exists(path)){
                Files.delete(path);
            }
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.ATOMIC_MOVE);
    }

    @Override
    public Long addImage(MultipartFile multipartFile) throws IOException {
        Images images = new Images();
        images.setContentType(multipartFile.getContentType());
        images.setName(multipartFile.getOriginalFilename());
        images.setFileSize(multipartFile.getSize());
        imagesRepository.save(images);
        String AA = multipartFile.getOriginalFilename();
        String fileName = String.valueOf(images.getId()) + AA.substring(AA.length() - 4, AA.length());
        images.setExtension(AA.substring(AA.length() - 4));
        System.out.println(fileStoragePath);
        Path filePath = Paths.get(fileStoragePath + "//" + fileName);
        images.setUploadPath(String.valueOf(filePath));
        Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        imagesRepository.save(images);
        return images.getId();
    }


}
