package com.qorakol.ilm.ziyo.controller;

import com.qorakol.ilm.ziyo.service.interfaces.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getTeachers(limit, page));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(value = "/get_student")
    public ResponseEntity getStudent(@RequestParam(name = "id") Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getStudent(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/get_students_new")
    public ResponseEntity getStudentsNew(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getStudentNew());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/get_students_payed")
    public ResponseEntity getStudentsPayed(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getStudentPayed());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/get_students_not_payed")
    public ResponseEntity getStudentsNotPayed(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getStudentNotPayed());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/images")
    public ResponseEntity images(@RequestParam Long id) throws MalformedURLException {
        try {
            return service.images(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/get_language")
    public ResponseEntity getSubjects(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllLang());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(value = "/get_subjects")
    public ResponseEntity getLang(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllSubjects());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(value = "/get_main_images")
    public ResponseEntity getMainImages(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getMainImages());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(value = "/get_group")
    public ResponseEntity getGroup(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getGroup());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }



}
