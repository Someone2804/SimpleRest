package com.SimpleRest.SimpleRestAPI.service;

import com.SimpleRest.SimpleRestAPI.store.dto.UserDTO;
import com.SimpleRest.SimpleRestAPI.store.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface UserService {

    UserDTO findById(long id);
    List<UserDTO> findAll();
    UserDTO saveUser(User user);
    void deleteUserById(long id);
}
