package com.SimpleRest.SimpleRestAPI.controllers;

import com.SimpleRest.SimpleRestAPI.service.PostService;
import com.SimpleRest.SimpleRestAPI.store.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDTO> getOne(@PathVariable long id){
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAll(){
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @GetMapping("image/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable String name) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        InputStream in = getClass().getResourceAsStream("/templates/" + name);
        if(in == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        byte[] media = in.readAllBytes();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteOne(@PathVariable long id){
        postService.deletePostById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/posts");
        return new ResponseEntity<String>(httpHeaders, HttpStatus.OK);
    }

}
