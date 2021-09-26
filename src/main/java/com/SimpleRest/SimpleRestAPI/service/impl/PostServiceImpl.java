package com.SimpleRest.SimpleRestAPI.service.impl;

import com.SimpleRest.SimpleRestAPI.exceptions.NotFoundException;
import com.SimpleRest.SimpleRestAPI.service.PostService;
import com.SimpleRest.SimpleRestAPI.store.dto.PostDTO;
import com.SimpleRest.SimpleRestAPI.store.entity.Post;
import com.SimpleRest.SimpleRestAPI.store.entity.User;
import com.SimpleRest.SimpleRestAPI.store.repo.PostRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostServiceImpl implements PostService {

    final PostRepo postRepo;

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
    public PostDTO savePost(Post post) {
        post.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return PostDTO.postToPostDTO(postRepo.saveAndFlush(post));
    }

    @Override
    public void deletePostById(long id) {
        findById(id);
        postRepo.deleteById(id);
    }
}
