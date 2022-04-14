package com.qorakol.ilm.ziyo.controller;

import com.qorakol.ilm.ziyo.service.interfaces.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

@RestController
@RequestMapping(value = "/static")
public class StaticController {

    private final StaticService service;


    public StaticController(StaticService service) {
        this.service = service;
    }


    @GetMapping(path = "/get_teachers_list")
    public ResponseEntity<Object> getTeachers(@RequestParam(name = "limit", defaultValue = "10", required = false) int limit, @RequestParam(name = "page", required = false, defaultValue = "0") int page){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getTeachers(limit, page));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/get_teachers_list_search")
    public ResponseEntity getTeachersList(@RequestParam(name = "name", defaultValue = "", required = false) String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getTeachersList(name));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/get_single_teacher")
    public ResponseEntity getSingleTeacher(@RequestParam(name = "id") Long id ){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getSingleTeacher(id));
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
    public ResponseEntity getStudentsNew(@RequestParam(name = "pageSize") int pageSize, @RequestParam(name = "current") int current){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getStudentNew(pageSize, current));
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
    public ResponseEntity getLang(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllLang());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(value = "/get_subjects")
    public ResponseEntity getSubjects(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllSubjects());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(value = "/get_subject_one")
    public ResponseEntity getViewSubject(@RequestParam Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getViewSubject(id));
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

    @GetMapping(value = "/get_group_list")
    public ResponseEntity getGroup(@RequestParam(name = "pageSize") int pageSize, @RequestParam(name = "current") int current){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getGroup(pageSize, current));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(value = "/get_group_one")
    public ResponseEntity getGroupOne(@RequestParam Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getGroupOne(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path ="/get_dashboard")
    public ResponseEntity getDashboard(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getDashboard());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path ="/line_graph")
    public ResponseEntity lineGraph(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.lineGraph());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

//    landing page

    @GetMapping(path = "/landing_teacher")
    public ResponseEntity landingTeacher(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.landingTeacher());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/landing_groups")
    public ResponseEntity landingGroups(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.landingGroups());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @GetMapping(path = "/landing_event")
    public ResponseEntity landingEvent(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.landingEvent());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }


}
