package com.qorakol.ilm.ziyo.controller;

import com.qorakol.ilm.ziyo.model.dto.RegStudentDto;
import com.qorakol.ilm.ziyo.model.dto.SToGroup;
import com.qorakol.ilm.ziyo.model.dto.StudentLogin;
import com.qorakol.ilm.ziyo.service.interfaces.DependService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/depend")
public class DependController {

    private final DependService dependService;

    public DependController(DependService dependService) {
        this.dependService = dependService;
    }

    @GetMapping(path = "/get_students")
    public ResponseEntity getStudentList(@RequestParam(name = "current") int current, @RequestParam(name = "pageSize") int pageSize ){
        return dependService.getStudentList(current, pageSize);
    }

    @GetMapping(path = "/get_Students_list_search")
    public ResponseEntity getTeachersList(@RequestParam(name = "name", defaultValue = "") String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dependService.getStudentSearchList(name));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/get_students_payed")
    public ResponseEntity getStudentsPayed(@RequestParam(name = "current") int current, @RequestParam(name = "pageSize") int pageSize){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dependService.getStudentPayed(current, pageSize));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @DeleteMapping(path = "/delete_new_student")
    public ResponseEntity deleteNewStudent(@RequestParam(name = "id") Long id){
        try {
            dependService.deleteStudent(id);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @PostMapping(value = "/add_student")
    public ResponseEntity<?> regStudent(@Valid @RequestBody RegStudentDto regStudentDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dependService.createStudent(regStudentDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @PostMapping(path = "/add_student_login")
    public ResponseEntity addStudentLogin(@Valid @RequestBody StudentLogin studentLogin){
        try {
            dependService.addStudentLogin(studentLogin);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @PostMapping(value = "/student_group")
    public ResponseEntity studentGroup(@Valid @RequestBody SToGroup sToGroup){
        try {
            dependService.studentAddGroup(sToGroup);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/get_group_list_search")
    public ResponseEntity getGroupsListSearch(@RequestParam(name = "name", defaultValue = "") String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dependService.getGroupListSearch(name));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/get_group_list_student")
    public ResponseEntity getGroupsListStudent(@RequestParam(name = "id") Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dependService.getGroupListStudent(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/get_student_list_by_group")
    public ResponseEntity getStudentListByGroup(@RequestParam(name = "id") Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dependService.getStudentListByGroup(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

}
