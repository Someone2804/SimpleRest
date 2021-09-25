package com.SimpleRest.SimpleRestAPI.store.repo;

import com.SimpleRest.SimpleRestAPI.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);
}
