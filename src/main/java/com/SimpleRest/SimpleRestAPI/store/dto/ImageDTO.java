package com.SimpleRest.SimpleRestAPI.store.dto;

import com.SimpleRest.SimpleRestAPI.store.entity.Image;
import com.SimpleRest.SimpleRestAPI.store.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageDTO {

    long id;

    String type;

    String name;

    public static ImageDTO imageToImageDTO(Image image){
        return builder().id(image.getId())
                .type(image.getType())
                .name(image.getName())
                .build();
    }

    public static List<ImageDTO> imageToImageDTOList(List<Image> images){
        return images.stream().map(ImageDTO::imageToImageDTO).collect(Collectors.toList());
    }
}
