package com.SimpleRest.SimpleRestAPI.service;

import com.SimpleRest.SimpleRestAPI.store.dto.ImageDTO;
import org.springframework.stereotype.Component;

@Component
public interface ImageService {

    ImageDTO findById(long id);
    byte[] imageToByte(String name);
    void deleteImage(long id);
}
