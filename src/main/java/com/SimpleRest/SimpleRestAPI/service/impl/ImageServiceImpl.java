package com.SimpleRest.SimpleRestAPI.service.impl;

import com.SimpleRest.SimpleRestAPI.exceptions.NotFoundException;
import com.SimpleRest.SimpleRestAPI.service.ImageService;
import com.SimpleRest.SimpleRestAPI.store.dto.ImageDTO;
import com.SimpleRest.SimpleRestAPI.store.entity.Image;
import com.SimpleRest.SimpleRestAPI.store.repo.ImageRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageServiceImpl implements ImageService {

    final ImageRepo imageRepo;

    @Autowired
    public ImageServiceImpl(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    @Override
    public ImageDTO findById(long id) {
        return ImageDTO.imageToImageDTO(imageRepo.findById(id).orElseThrow(() -> new NotFoundException("User id not found")));
    }

    @Override
    public ImageDTO saveImage(Image image) {
        return null;
    }

    @Override
    public void deleteImage(long id) {

    }
}
