package com.SimpleRest.SimpleRestAPI.service.impl;

import com.SimpleRest.SimpleRestAPI.exceptions.AlreadyExistException;
import com.SimpleRest.SimpleRestAPI.exceptions.NotFoundException;
import com.SimpleRest.SimpleRestAPI.service.UserService;
import com.SimpleRest.SimpleRestAPI.store.dto.UserDTO;
import com.SimpleRest.SimpleRestAPI.store.entity.ERole;
import com.SimpleRest.SimpleRestAPI.store.entity.Post;
import com.SimpleRest.SimpleRestAPI.store.entity.Role;
import com.SimpleRest.SimpleRestAPI.store.entity.User;
import com.SimpleRest.SimpleRestAPI.store.repo.RoleRepo;
import com.SimpleRest.SimpleRestAPI.store.repo.UserRepo;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService, UserDetailsService {

    final UserRepo userRepo;
    final RoleRepo roleRepo;
    final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }


    @Override
    public List<UserDTO> findAll() {
        return UserDTO.userToUserDTOList(userRepo.findAll());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + s + " not found."));
    }

    @Override
    public UserDTO findById(@NotNull long id) {
        return UserDTO.userToUserDTO(userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id: " + id + " not found.")));
    }

    @Override
    public UserDTO saveUser(@NotNull User user) {
        if(userRepo.findByUsername(user.getUsername()).isPresent()){
            throw new AlreadyExistException("Username " + user.getUsername() + " already exist.");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setPosts(new ArrayList<Post>());
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleRepo.findByRole(ERole.USER.name()));
        user.setRoles(roles);
        return UserDTO.userToUserDTO(userRepo.saveAndFlush(user));
    }

    @Override
    public void deleteUserById(long id) {
        findById(id);
        userRepo.deleteById(id);
    }
}
