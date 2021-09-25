package com.SimpleRest.SimpleRestAPI.service;

import com.SimpleRest.SimpleRestAPI.store.dto.PostDTO;
import com.SimpleRest.SimpleRestAPI.store.dto.RoleDTO;
import com.SimpleRest.SimpleRestAPI.store.entity.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface RoleService {

    RoleDTO findById(long id);
    List<RoleDTO> findAll();
    RoleDTO saveRole(Role role);
    void deleteRole(long id);
}
