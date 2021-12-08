package com.qorakol.ilm.ziyo.controller;

import com.qorakol.ilm.ziyo.service.interfaces.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequestMapping(value = "/static")
public class StaticController {

    private final StaticService service;

    @Autowired
    public StaticController(StaticService service) {
        this.service = service;
    }


    @GetMapping(path = "/get_teachers_list")
    public ResponseEntity getTeachers(@RequestParam(name = "limit") int limit, @RequestParam(name = "page") int page){
        return ResponseEntity.ok(service.getTeachers(limit, page));
    }

    @GetMapping(value = "/get_student")
    public ResponseEntity getStudent(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(service.getStudent(id));
    }

    @GetMapping(path = "/get_students_new")
    public ResponseEntity getStudentsNew(){
        return ResponseEntity.ok(service.getStudentNew());
    }

    @GetMapping(path = "/get_students_payed")
    public ResponseEntity getStudentsPayed(){
        return ResponseEntity.ok(service.getStudentPayed());
    }

    @GetMapping(path = "/get_students_not_payed")
    public ResponseEntity getStudentsNotPayed(){
        return ResponseEntity.ok(service.getStudentNotPayed());
    }

    @GetMapping(path = "/images")
    public ResponseEntity images(@RequestParam Long id) throws MalformedURLException {
        return service.images(id);
    }

    @GetMapping(path = "/get_language")
    public ResponseEntity getSubjects(){
        return ResponseEntity.ok(service.getAllLang());
    }

    @GetMapping(value = "/get_subjects")
    public ResponseEntity getLang(){
        return ResponseEntity.ok(service.getAllSubjects());
    }

    @GetMapping(value = "/get_main_images")
    public ResponseEntity getMainImages(){
        return ResponseEntity.ok(service.getMainImages());
    }

    @GetMapping(value = "/get_group")
    public ResponseEntity getGroup(){
        return ResponseEntity.ok(service.getGroup());
    }



}
