package com.SimpleRest.SimpleRestAPI.store.dto;

import com.SimpleRest.SimpleRestAPI.store.entity.Role;
import com.SimpleRest.SimpleRestAPI.store.entity.User;
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
public class RoleDTO {

    long id;

    String role;

    Set<UserDTO> users;

    public static RoleDTO roleToRoleDTO(Role role){
        return builder()
                .id(role.getId())
                .role(role.getRole())
                .users(imageDTOList(role.getUsers()))
                .build();
    }

    public static Set<UserDTO> imageDTOList(Set<User> userSet){
        return userSet.stream().map((e) -> {
            return UserDTO.builder()
                    .id(e.getId())
                    .name(e.getUsername())
                    .build();
        }).collect(Collectors.toSet());
    }

    public static List<RoleDTO> roleToRoleDTOList(List<Role> roles){
        return roles.stream().map(RoleDTO::roleToRoleDTO).collect(Collectors.toList());
    }
}
