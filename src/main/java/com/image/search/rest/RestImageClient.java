package com.image.search.rest;

import com.image.search.config.ImageProperties;
import com.image.search.model.ImagePaginationResult;
import com.image.search.model.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestImageClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ImageProperties urlProperties;

    public ImagePaginationResult getPicturesPage(int currentPage) {
        return restTemplate.getForEntity(
                urlProperties.getBase() + urlProperties.getImages() + "?page=" + currentPage, ImagePaginationResult.class)
                .getBody();
    }

    public Image getImageById(String id) {
        return restTemplate.getForEntity(
                urlProperties.getBase() + urlProperties.getImages() + "/" + id, Image.class)
                .getBody();
    }
}
