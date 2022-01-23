package com.qorakol.ilm.ziyo.controller;

import com.qorakol.ilm.ziyo.constant.reg.Registers;
import com.qorakol.ilm.ziyo.model.dto.*;
import com.qorakol.ilm.ziyo.service.interfaces.AdminService;
import com.qorakol.ilm.ziyo.service.interfaces.GroupService;
import com.qorakol.ilm.ziyo.service.interfaces.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/a23d_m23_i23n")
public class AdminController {

    private final GroupService groupService;
    private final StaticService service;
    private final AdminService adminService;

    @Autowired
    public AdminController(GroupService groupService, StaticService service, AdminService adminService) {
        this.groupService = groupService;
        this.service = service;
        this.adminService = adminService;
    }

    @PostMapping(value = "/add_teacher",
            consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<?> regTeacher(@Valid @ModelAttribute RegTeacherDto regTeacherDto){
        try {
            adminService.createTeacher(regTeacherDto);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }


    @GetMapping(path = "/remove_student_group")
    public ResponseEntity removeStudent(@RequestParam Long studentId, @RequestParam Long groupId){
        try {
            adminService.removeStudentFromGroup(studentId,groupId);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @PostMapping(value = "/add_group",
            consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity addGroup(@Valid @ModelAttribute NewGroup newGroup){
        try{
            adminService.save(newGroup);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @PutMapping(value = "/put_group/{id}",
            consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity putGroup(@Valid @ModelAttribute NewGroup newGroup, @PathVariable Long id){
        try{
            adminService.changeGroup(newGroup, id);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @DeleteMapping(path = "/delete_group/{id}")
    public ResponseEntity deleteGroup(@PathVariable(name = "id") Long id){
        try{
//            adminService.changeGroup(newGroup, id);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @PostMapping(value = "/main_image")
    public ResponseEntity mainImage(@ModelAttribute MainImageDto mainImageDto){
        try{
            adminService.addMainImage(mainImageDto);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @PutMapping(value = "/put_main_image")
    public ResponseEntity putMainImage(@ModelAttribute MainImageDto mainImageDto){
        try{
            adminService.putImage(mainImageDto);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }


    @DeleteMapping(value = "/delete_image")
    public ResponseEntity deleteImage(@RequestParam Long id){
        try{
//            adminService.deleteImage(id);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @PostMapping(value = "/payment")
    public ResponseEntity payment(@RequestBody PaymentDto paymentDto){
        try{
            adminService.paying(paymentDto);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @PutMapping(value = "/change_payment")
    public ResponseEntity changePayment(@RequestBody PaymentDto paymentDto){
        try{
            adminService.changePayment(paymentDto);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }



//    @Value bu yerda subject larni CRUD i bor

    @PostMapping(value = "/add_subject")
    public ResponseEntity addSubject(@RequestBody SubjectDto subjectDto){
        try{
            service.addSubject(subjectDto);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @PutMapping(value = "/put_subject/{id}")
    public ResponseEntity putSubject(@PathVariable(name = "id") Long id, @RequestBody SubjectDto subjectDto){
        try{
            service.putSubject(id, subjectDto);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @DeleteMapping(value = "/delete_subject/{id}")
    public ResponseEntity deleteSubject(@PathVariable(name = "id") Long id){
        try{
            service.deleteSubject(id);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

//    Events CRUD

    @PostMapping(path = "/add_event")
    public ResponseEntity addEvent(@Valid @RequestBody EventDto eventDto){
        try{
            adminService.addEvent(eventDto);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @PutMapping(path = "/put_event/{id}")
    public ResponseEntity changeEvent(@PathVariable long id, @RequestBody EventDto eventDto){
        try{
            adminService.changeEvent(eventDto, id);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

    @DeleteMapping(path = "/delete_event/{id}")
    public ResponseEntity deleteEvent(@PathVariable long id){
        try{
            adminService.deleteEvent(id);
            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ERROR");
        }
    }

}
