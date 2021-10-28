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

    UserDTO user;

    public static PostDTO postToPostDTO(Post post){
        UserDTO userDTO = UserDTO.userToUserDTO(post.getUser());
        userDTO.setPosts(null);
        return builder()
                .id(post.getId())
                .head(post.getHead())
                .text(post.getText())
                .images(imageDTOList(post.getImages()))
                .user(userDTO)
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
