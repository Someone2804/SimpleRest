package com.SimpleRest.SimpleRestAPI.service.impl;

import com.SimpleRest.SimpleRestAPI.exceptions.NotFoundException;
import com.SimpleRest.SimpleRestAPI.service.ImageService;
import com.SimpleRest.SimpleRestAPI.store.dto.ImageDTO;
import com.SimpleRest.SimpleRestAPI.store.repo.ImageRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageServiceImpl implements ImageService {

    final ImageRepo imageRepo;

    @Value("${images.path}")
    String path;

    @Autowired
    public ImageServiceImpl(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    @Override
    public ImageDTO findById(long id) {
        return ImageDTO.imageToImageDTO(imageRepo.findById(id).orElseThrow(() -> new NotFoundException("User id not found")));
    }

    @Override
    public ImageDTO findByName(String name) {
        return ImageDTO.imageToImageDTO(imageRepo.findByName(name).orElseThrow(() -> new NotFoundException("User id not found")));
    }

    @Override
    public void deleteImage(long id) {
        findById(id);
        imageRepo.deleteById(id);
    }

    @Override
    public byte[] imageToByte(String name) {
        byte[] media;
        try {
            media = Files.readAllBytes(Path.of(path + "/" + name));
        } catch (IOException e) {
            throw new NotFoundException("Image " + name + " not found");
        }

        return media;

    }
}
