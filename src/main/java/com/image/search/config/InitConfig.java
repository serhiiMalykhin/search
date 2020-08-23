package com.image.search.config;

import com.image.search.model.ImagePaginationResult;
import com.image.search.model.Picture;
import com.image.search.model.entity.Image;
import com.image.search.rest.RestImageClient;
import com.image.search.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class InitConfig {

    @Autowired
    private RestImageClient client;

    @Autowired
    private ImageService imageService;

    @PostConstruct
    void init() {
        List<Picture> pictures = fetchPictures();
        List<Image> images = fetchImages(pictures);
        imageService.storeImages(images);
    }

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
        return pictures.stream()
                .map(picture -> client.getImageById(picture.getId()))
                .collect(Collectors.toList());
    }
}
