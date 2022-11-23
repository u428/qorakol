package com.qorakol.ilm.ziyo.service;

import com.qorakol.ilm.ziyo.constant.RoleContants;
import com.qorakol.ilm.ziyo.constant.StudentStatus;
import com.qorakol.ilm.ziyo.model.dto.AdminDto;
import com.qorakol.ilm.ziyo.model.entity.*;
import com.qorakol.ilm.ziyo.repository.*;
import com.qorakol.ilm.ziyo.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final ActivationRepository activationRepository;
    private final ActivationDetailsRepository activationDetailsRepository;
    private final ImagesRepository imagesRepository;
    private final RoleRepository roleRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final GroupsRepository groupsRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final LanguageRepository languageRepository;
    private final SubjectsRepository subjectsRepository;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository, ActivationRepository activationRepository, ActivationDetailsRepository activationDetailsRepository, ImagesRepository imagesRepository, RoleRepository roleRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, GroupsRepository groupsRepository, BCryptPasswordEncoder bCryptPasswordEncoder, LanguageRepository languageRepository, SubjectsRepository subjectsRepository) {
        this.authRepository = authRepository;
        this.activationRepository = activationRepository;
        this.activationDetailsRepository = activationDetailsRepository;
        this.imagesRepository = imagesRepository;
        this.roleRepository = roleRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.groupsRepository = groupsRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.languageRepository = languageRepository;
        this.subjectsRepository = subjectsRepository;
    }

    @Override
    public boolean checkLogin(String login) {
        return authRepository.existsByLogin(login);
    }


    @Override
    public Map<String, Object> getCurrentUser(String login){
        System.out.println(login);
        AuthEntity authEntity = authRepository.findByLogin(login);
        Teacher teacher = teacherRepository.findByAuthEntity(authEntity);
        Student student = null;
        Map<String, Object> result = new HashMap<>();
        if (teacher == null){
            student = studentRepository.findByAuthEntity(authEntity);
            student.setAuthEntity(null);
            student.setId(null);
            student.setAuthId(null);
            student.setActivation(null);
            result.put("user", student);
        }else{
            teacher.setLanguages(null);
            teacher.setSubjects(null);
            teacher.setAuthEntity(null);
            result.put("user", teacher);
        }
        result.put("role", authEntity.getRoles());
        return result;
    }



    @Override
    public void addAdmin(AdminDto adminDto) throws Exception {
        Student student = new Student();
        student.setStatus(StudentStatus.YANGI);
        student.setFirstName(adminDto.getFirstName());
        student.setLastName(adminDto.getLastName());
        student.setTelNomer(adminDto.getTelNomer());
        AuthEntity authEntity=new AuthEntity();
        authEntity.setLogin(adminDto.getLogin());
        authEntity.setPassword(bCryptPasswordEncoder.encode(adminDto.getPassword()));
        Roles roles = roleRepository.findByName(RoleContants.ADMIN);
        authEntity.setRolesId(roles.getId());
        authRepository.save(authEntity);
        student.setAuthId(authEntity.getId());
        studentRepository.save(student);
    }



    @Override
    public Roles getRoles(String login) throws Exception {
        return authRepository.findByLogin(login).getRoles();
    }

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthEntity user=authRepository.findByLogin(s);
        if (user == null) throw new UsernameNotFoundException(s);
        return new User(String.valueOf(user.getLogin()), user.getPassword(), getAuthority(user));
    }
    private Set<SimpleGrantedAuthority> getAuthority(AuthEntity authEntity) {
        Set<SimpleGrantedAuthority> authorities =new HashSet<>();
//        Set<SimpleGrantedAuthority> authorities = priviligesRepository.findAllByRoleId(customer.getRole().getId()).stream().map(priviliges ->
//                new SimpleGrantedAuthority(priviliges.getName())).collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+authEntity.getRoles().getName()));
        return authorities;
    }

    @Override
    public void changePassword(String password, Long authId) {
        AuthEntity authEntity = authRepository.findById(authId).get();
        if (authEntity == null) throw new UsernameNotFoundException("authId");

        authEntity.setPassword(bCryptPasswordEncoder.encode(password));
        authRepository.save(authEntity);
    }

    @Override
    public void addRole(){
        Roles roles = new Roles();
        roles.setLevel(1);
        roles.setName(RoleContants.SUPER_ADMIN);
        roleRepository.save(roles);
        Roles roles2 = new Roles();
        roles2.setLevel(2);
        roles2.setName(RoleContants.ADMIN);
        roleRepository.save(roles2);
        Roles roles3 = new Roles();
        roles3.setLevel(3);
        roles3.setName(RoleContants.MODERATOR);
        roleRepository.save(roles3);
        Roles roles4 = new Roles();
        roles4.setLevel(4);
        roles4.setName(RoleContants.TEACHER);
        roleRepository.save(roles4);
        Roles roles5 = new Roles();
        roles5.setLevel(5);
        roles5.setName(RoleContants.STUDENT);
        roleRepository.save(roles5);
        Language language = new Language();
        language.setName("English");
        Language language2 = new Language();
        language2.setName("Русский");
        Language language3 = new Language();
        language3.setName("O'zbek");
        languageRepository.save(language3);
        languageRepository.save(language2);
        languageRepository.save(language);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setLogin("admin");
        authEntity.setPassword(bCryptPasswordEncoder.encode("admin"));
        authEntity.setRolesId(roles2.getId());
        authRepository.save(authEntity);

        Teacher teacher = new Teacher();
        teacher.setFirstName("Admin2");
        teacher.setLastName("Admin2");
        teacher.setDelete(true);
        teacher.setAuthId(authEntity.getId());
        teacherRepository.save(teacher);

        AuthEntity authEntity2 = new AuthEntity();
        authEntity2.setLogin("super_admin");
        authEntity2.setPassword(bCryptPasswordEncoder.encode("super_admin"));
        authEntity2.setRolesId(roles.getId());
        authRepository.save(authEntity2);

        Teacher superadmin = new Teacher();
        superadmin.setFirstName("Admin3");
        superadmin.setLastName("Admin3");
        superadmin.setDelete(true);
        superadmin.setAuthId(authEntity.getId());
        teacherRepository.save(superadmin);



    }
}
