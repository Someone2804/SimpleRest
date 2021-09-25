package com.SimpleRest.SimpleRestAPI.service;

import com.SimpleRest.SimpleRestAPI.store.dto.PostDTO;
import com.SimpleRest.SimpleRestAPI.store.entity.Post;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface PostService {

    PostDTO findById(long id);
    List<PostDTO> findAll();
    PostDTO savePost(Post post);
    void deletePostById(long id);

}
