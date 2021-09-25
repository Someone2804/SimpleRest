package com.SimpleRest.SimpleRestAPI.store.repo;

import com.SimpleRest.SimpleRestAPI.store.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo  extends JpaRepository<Post, Long> {
}
