package com.SimpleRest.SimpleRestAPI.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {

    @GetMapping
    public ResponseEntity<?> mainPage(){
        return new ResponseEntity<String>(HttpStatus.OK);
    }


}
