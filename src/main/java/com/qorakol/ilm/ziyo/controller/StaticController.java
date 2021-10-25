package com.qorakol.ilm.ziyo.controller;

import com.qorakol.ilm.ziyo.service.interfaces.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/static")
public class StaticController {

    private final StaticService service;

    @Autowired
    public StaticController(StaticService service) {
        this.service = service;
    }

    @GetMapping(value = "/get_language")
    public ResponseEntity getLang(){
        return ResponseEntity.ok(service.getAllLang());
    }
}
