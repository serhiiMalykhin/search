package com.image.search.rest;

import com.image.search.config.ImageProperties;
import com.image.search.model.ImagePaginationResult;
import com.image.search.model.converter.ImageConverter;
import com.image.search.model.dto.ImageDto;
import com.image.search.model.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestImageClient {

    private final RestTemplate restTemplate;
    private final ImageProperties urlProperties;
    private final ImageConverter converter;

    public ImagePaginationResult getPicturesPage(int currentPage) {
        return restTemplate.getForEntity(
                urlProperties.getBase() + urlProperties.getImages() + "?page=" + currentPage, ImagePaginationResult.class)
                .getBody();
    }

    public Image getImageById(String id) {
        ImageDto imageDto = restTemplate.getForEntity(
                urlProperties.getBase() + urlProperties.getImages() + "/" + id,
                ImageDto.class)
                .getBody();
        return converter.convert(imageDto);
    }
}
