package com.qorakol.ilm.ziyo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @GetMapping(value ="/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.ok("Hello");
    }

}
