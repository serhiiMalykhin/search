package com.image.search.service;

import com.image.search.model.SearchRequest;
import com.image.search.model.entity.Image;
import com.image.search.repository.ImageRepository;
import com.image.search.repository.ImageSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public void storeImages(List<Image> images) {
        imageRepository.saveAll(images);
    }

    @Cacheable(value = "imagesCache", key = "#searchRequest")
    public List<Image> search(SearchRequest searchRequest) {
        return imageRepository.findAll(Specification.where(new ImageSpecification(searchRequest)));
    }

}
