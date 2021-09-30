package com.SimpleRest.SimpleRestAPI.service;

import com.SimpleRest.SimpleRestAPI.store.dto.ImageDTO;
import org.springframework.stereotype.Component;

@Component
public interface ImageService {

    ImageDTO findById(long id);
    ImageDTO findByName(String name);
    byte[] imageToByte(String name);
    void deleteImage(long id);
}
