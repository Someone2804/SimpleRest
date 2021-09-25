package com.SimpleRest.SimpleRestAPI.service;

import com.SimpleRest.SimpleRestAPI.store.dto.ImageDTO;
import com.SimpleRest.SimpleRestAPI.store.entity.Image;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface ImageService {

    ImageDTO findById(long id);
    ImageDTO saveImage(Image image);
    void deleteImage(long id);
}
