package com.image.search.rest;

import com.image.search.model.ImagePaginationResult;
import com.image.search.model.dto.Picture;
import com.image.search.model.entity.Image;
import com.image.search.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageClient {

    private final RestImageClient client;

    private final ImageService imageService;

    public List<Picture> fetchPictures() {
        List<Picture> pictures = new ArrayList<>();
        int currentPage = 0;
        ImagePaginationResult result;
        do {
            currentPage++;
            result = client.getPicturesPage(currentPage);
            if (!result.getPictures().isEmpty()) {
                pictures.addAll(result.getPictures());
            }
        } while (result.getHasMorePages() != null && result.getHasMorePages());

        return pictures;
    }

    public List<Image> fetchImages(List<Picture> pictures) {
        List<Image> images = pictures.stream()
                .map(picture -> {
                    if (imageService.existById(picture.getId())) {
                        return null;
                    }
                    return client.getImageById(picture.getId());
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        imageService.storeImages(images);
        return images;
    }

}
