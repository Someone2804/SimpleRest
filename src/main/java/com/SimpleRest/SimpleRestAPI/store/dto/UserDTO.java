package com.SimpleRest.SimpleRestAPI.store.dto;

import com.SimpleRest.SimpleRestAPI.store.entity.Post;
import com.SimpleRest.SimpleRestAPI.store.entity.Role;
import com.SimpleRest.SimpleRestAPI.store.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDTO {

    long id;

    String name;

    @JsonIgnore
    String password;

    Set<RoleDTO> roles;

    List<PostDTO> posts;

    public static UserDTO userToUserDTO(User user){
        return builder().id(user.getId())
                .name(user.getUsername())
                .roles(roleDTOSet(user.getRoles()))
                .posts(postDTOList(user.getPosts()))
                .build();
    }

    public static Set<RoleDTO> roleDTOSet(Set<Role> roleSet){
        return roleSet.stream().map((e) -> {
            return RoleDTO.builder()
                    .id(e.getId())
                    .role(e.getRole())
                    .build();
        }).collect(Collectors.toSet());
    }

    public static List<PostDTO> postDTOList(List<Post> postList){
        return postList.stream().map((e) -> {
            return PostDTO.builder()
                    .id(e.getId())
                    .head(e.getHead())
                    .build();
        }).collect(Collectors.toList());
    }

    public static List<UserDTO> userToUserDTOList(List<User> users){
        return users.stream().map(UserDTO::userToUserDTO).collect(Collectors.toList());
    }
}
