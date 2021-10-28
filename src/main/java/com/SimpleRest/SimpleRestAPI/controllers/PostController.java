package com.SimpleRest.SimpleRestAPI.controllers;

import com.SimpleRest.SimpleRestAPI.service.ImageService;
import com.SimpleRest.SimpleRestAPI.service.PostService;
import com.SimpleRest.SimpleRestAPI.store.dto.PostDTO;
import com.SimpleRest.SimpleRestAPI.store.entity.Post;
import com.SimpleRest.SimpleRestAPI.store.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    final PostService postService;
    final ImageService imageService;

    @Autowired
    public PostController(PostService postService, ImageService imageService) {
        this.postService = postService;
        this.imageService = imageService;
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDTO> getOne(@PathVariable long id){
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAll(){
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestPart(value = "images", required = false) List<MultipartFile> multipartFile, @RequestParam String head, @RequestParam String text, @AuthenticationPrincipal User user){
        Post post = new Post();
        post.setHead(head);
        post.setText(text);
        PostDTO postDTO = postService.savePost(post, multipartFile, user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/posts/" + postDTO.getId());
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
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
