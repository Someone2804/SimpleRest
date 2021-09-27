package com.SimpleRest.SimpleRestAPI.controllers;

import com.SimpleRest.SimpleRestAPI.store.dto.UserDTO;
import lombok.AccessLevel;
import com.SimpleRest.SimpleRestAPI.service.UserService;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteOne(@PathVariable long id){
        userService.deleteUserById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/users");
        return new ResponseEntity<String>(httpHeaders, HttpStatus.OK);
    }
}
