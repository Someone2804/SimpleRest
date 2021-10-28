package com.SimpleRest.SimpleRestAPI.service;

import com.SimpleRest.SimpleRestAPI.store.dto.PostDTO;
import com.SimpleRest.SimpleRestAPI.store.entity.Post;
import com.SimpleRest.SimpleRestAPI.store.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface PostService {

    PostDTO findById(long id);
    List<PostDTO> findAll();
    PostDTO savePost(Post post, List<MultipartFile> multipartFile, User user);
    void deletePostById(long id);

}
