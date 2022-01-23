package com.qorakol.ilm.ziyo.controller;

import com.qorakol.ilm.ziyo.model.dto.CheckStudents;
import com.qorakol.ilm.ziyo.security.CurrentUser;
import com.qorakol.ilm.ziyo.service.interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @PostMapping(value = "/student_check")
    public ResponseEntity studentCheck(@CurrentUser String login, @RequestBody CheckStudents checkStudents){
        try {
            teacherService.checkStudent(login, checkStudents);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }


    @GetMapping(value = "/groups")
    public ResponseEntity getRoles(@CurrentUser String login){
//        return ResponseEntity.ok(teacherService.getGroups(login));
        try {
            return ResponseEntity.status(HttpStatus.OK).body("SUCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(value ="group_students")
    public ResponseEntity getGroupStudents(@CurrentUser String login, @RequestParam(value = "id") Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(teacherService.getGroupStudents(login, id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }
}
