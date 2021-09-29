package com.SimpleRest.SimpleRestAPI.controllers;


import com.SimpleRest.SimpleRestAPI.service.ImageService;
import com.SimpleRest.SimpleRestAPI.service.PostService;
import com.SimpleRest.SimpleRestAPI.store.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts/{id}")
public class ImageController {

    ImageService imageService;
    PostService postService;

    @Autowired
    public ImageController(ImageService imageService, PostService postService) {
        this.imageService = imageService;
        this.postService = postService;
    }
    @GetMapping("/images")
    public ResponseEntity<byte[][]> getAll(@PathVariable long id){
        List<ImageDTO> images = postService.findById(id).getImages();
        byte[][] imagesb = new byte[images.size()][];
        int i = 0;
        for (ImageDTO e : images) {
            imagesb[i] = imageService.imageToByte(e.getName());
            i++;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(imagesb, headers, HttpStatus.OK);
    }

    @GetMapping("/images/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable long id, @PathVariable String name){
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        headers.set("Content-Type", "image/jpg");
        return new ResponseEntity<>(imageService.imageToByte(name), headers, HttpStatus.OK);
    }
}
