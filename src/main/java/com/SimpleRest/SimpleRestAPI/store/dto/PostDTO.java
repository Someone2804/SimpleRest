package com.SimpleRest.SimpleRestAPI.store.dto;


import com.SimpleRest.SimpleRestAPI.store.entity.Image;
import com.SimpleRest.SimpleRestAPI.store.entity.Post;
import com.SimpleRest.SimpleRestAPI.store.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDTO {

    long id;

    String head;

    String text;

    List<ImageDTO> images;

    User user;

    public static PostDTO postToPostDTO(Post post){
        return builder()
                .id(post.getId())
                .head(post.getHead())
                .text(post.getText())
                .images(imageDTOList(post.getImages()))
                .user(post.getUser())
                .build();
    }

    public static List<ImageDTO> imageDTOList(List<Image> imageList){
        return imageList.stream().map((e) -> {
            return ImageDTO.builder()
                    .id(e.getId())
                    .type(e.getType())
                    .name(e.getName())
                    .build();
        }).collect(Collectors.toList());
    }


    public static List<PostDTO> postToPostDTOList(List<Post> posts){
        return posts.stream().map(PostDTO::postToPostDTO).collect(Collectors.toList());
    }

}
