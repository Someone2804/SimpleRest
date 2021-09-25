package com.SimpleRest.SimpleRestAPI.store.repo;

import com.SimpleRest.SimpleRestAPI.store.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo  extends JpaRepository<Image, Long> {
}