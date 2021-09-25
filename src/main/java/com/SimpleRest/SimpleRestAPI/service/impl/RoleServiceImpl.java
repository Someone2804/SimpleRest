package com.SimpleRest.SimpleRestAPI.service.impl;

import com.SimpleRest.SimpleRestAPI.service.RoleService;
import com.SimpleRest.SimpleRestAPI.store.dto.RoleDTO;
import com.SimpleRest.SimpleRestAPI.store.entity.Role;
import com.SimpleRest.SimpleRestAPI.store.repo.RoleRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleServiceImpl implements RoleService {

    final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<RoleDTO> findAll() {
        return RoleDTO.roleToRoleDTOList(roleRepo.findAll());
    }

    @Override
    public RoleDTO findById(long id) {
        return null;
    }

    @Override
    public RoleDTO saveRole(Role role) {
        return null;
    }

    @Override
    public void deleteRole(long id) {

    }
}
