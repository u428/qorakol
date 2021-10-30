package com.qorakol.ilm.ziyo.controller;

import com.qorakol.ilm.ziyo.model.dto.MainImageDto;
import com.qorakol.ilm.ziyo.model.dto.NewGroup;
import com.qorakol.ilm.ziyo.model.dto.SubjectDto;
import com.qorakol.ilm.ziyo.service.interfaces.AdminService;
import com.qorakol.ilm.ziyo.service.interfaces.GroupService;
import com.qorakol.ilm.ziyo.service.interfaces.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/add_group",
            consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity addGroup(@Valid @ModelAttribute NewGroup newGroup){
        adminService.save(newGroup);
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping(value = "/set_photo")
    public ResponseEntity setPhoto(MultipartFile multipartFile){

        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping(value = "/add_subject")
    public ResponseEntity addSubject(@RequestBody SubjectDto subjectDto){
        service.addSubject(subjectDto);
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping(value = "/main_image")
    public ResponseEntity mainImage(@ModelAttribute MainImageDto mainImageDto){
        return ResponseEntity.ok( adminService.addMainImage(mainImageDto));
    }

    @PutMapping(value = "put_main_image")
    public ResponseEntity putMainImage(@ModelAttribute MainImageDto mainImageDto){
        return ResponseEntity.ok(adminService.putImage())
    }



}
