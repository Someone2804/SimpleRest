package com.SimpleRest.SimpleRestAPI.controllers;


import com.SimpleRest.SimpleRestAPI.service.UserService;
import com.SimpleRest.SimpleRestAPI.store.entity.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginAndRegistrationController {

    final UserService userService;

    @Autowired
    public LoginAndRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestPart("username") String username, @RequestPart("password") String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.saveUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/login");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
