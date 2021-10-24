package com.qorakol.ilm.ziyo.controller;

import com.qorakol.ilm.ziyo.model.dto.NewGroup;
import com.qorakol.ilm.ziyo.service.interfaces.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/a23d_m23_i23n")
public class AdminController {

    private final GroupService groupService;

    public AdminController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping(value = "add_group")
    public ResponseEntity addGroup(@Valid @RequestBody NewGroup newGroup){
        groupService.save(newGroup);
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping(value = "set_photo")
    public ResponseEntity setPhoto(){
        return ResponseEntity.ok("SUCCESS");
    }


}
