package com.SimpleRest.SimpleRestAPI.controllers;


import com.SimpleRest.SimpleRestAPI.service.ImageService;
import com.SimpleRest.SimpleRestAPI.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts/{id}")
public class ImageController {

    ImageService imageService;
    PostService postService;
    @Value("${images.path}")
    String path;

    @Autowired
    public ImageController(ImageService imageService, PostService postService) {
        this.imageService = imageService;
        this.postService = postService;
    }

    @GetMapping("/images/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable long id, @PathVariable String name){
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        headers.set("Content-Type", imageService.findByName(name).getType());
        return new ResponseEntity<>(imageService.imageToByte(name), headers, HttpStatus.OK);
    }
}
