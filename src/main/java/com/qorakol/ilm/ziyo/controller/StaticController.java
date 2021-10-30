package com.qorakol.ilm.ziyo.controller;

import com.qorakol.ilm.ziyo.repository.MainImagesRepository;
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

    @GetMapping(value = "/images")
    public ResponseEntity images(@RequestParam Long id) throws MalformedURLException {
        return service.images(id);
    }

    @GetMapping(value = "/get_language")
    public ResponseEntity getSubjects(){
        return ResponseEntity.ok(service.getAllSubjects());
    }

    @GetMapping(value = "/get_subjects")
    public ResponseEntity getLang(){
        return ResponseEntity.ok(service.getAllLang());
    }

    @GetMapping(value = "/get_main_images")
    public ResponseEntity getMainImages(){
        return ResponseEntity.ok(service.getMainImages());
    }


}
