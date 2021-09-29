package com.SimpleRest.SimpleRestAPI.service.impl;

import com.SimpleRest.SimpleRestAPI.exceptions.NotFoundException;
import com.SimpleRest.SimpleRestAPI.service.PostService;
import com.SimpleRest.SimpleRestAPI.store.dto.PostDTO;
import com.SimpleRest.SimpleRestAPI.store.entity.Image;
import com.SimpleRest.SimpleRestAPI.store.entity.Post;
import com.SimpleRest.SimpleRestAPI.store.entity.User;
import com.SimpleRest.SimpleRestAPI.store.repo.PostRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostServiceImpl implements PostService {

    final PostRepo postRepo;
    @Value("${images.path}")
    String path;

    @Autowired
    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public List<PostDTO> findAll() {
        return PostDTO.postToPostDTOList(postRepo.findAll());
    }

    @Override
    public PostDTO findById(long id) {
        return PostDTO.postToPostDTO(postRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Post with id " + id + " not found")));
    }

    @Override
    public PostDTO savePost(Post post, List<MultipartFile> multipartFile) {
        Image image;
        List<Image> images = new ArrayList<>();
        for (MultipartFile e : multipartFile) {
            try {
                String name = UUID.randomUUID() + "_" + e.getOriginalFilename();
                String s = path + name;
                e.transferTo(new File(s));
                image = new Image();
                image.setName(name);
                image.setType(e.getContentType());
                images.add(image);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        post.setImages(images);
        postRepo.saveAndFlush(post);
        return PostDTO.postToPostDTO(postRepo.saveAndFlush(post));
    }

    @Override
    public void deletePostById(long id) {
        findById(id);
        postRepo.deleteById(id);
    }
}
