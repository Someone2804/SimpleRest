package com.SimpleRest.SimpleRestAPI.store.repo;

import com.SimpleRest.SimpleRestAPI.store.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    @Modifying
    @Query("delete from Post p where p.id = :id")
    void deleteById(@Param("id") Long id);
}
