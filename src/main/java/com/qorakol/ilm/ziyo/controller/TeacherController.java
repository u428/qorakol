package com.qorakol.ilm.ziyo.controller;

import com.qorakol.ilm.ziyo.security.CurrentUser;
import com.qorakol.ilm.ziyo.service.interfaces.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


//    @PostMapping(value = "/student_check")
//    public ResponseEntity studentCheck(@CurrentUser String login, List<Long> ids){
//        return ResponseEntity.ok(teacherService.checkStudent(login, ids));
//    }


    @GetMapping(value = "/groups")
    public ResponseEntity getRoles(@CurrentUser String login){
        return ResponseEntity.ok(teacherService.getGroups(login));
    }

    @GetMapping(value ="group_students")
    public ResponseEntity getGroupStudents(@CurrentUser String login, @RequestParam(value = "id") Long id){
        return ResponseEntity.ok(teacherService.getGroupStudents(login, id));
    }
}
