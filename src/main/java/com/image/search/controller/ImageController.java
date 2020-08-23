package com.image.search.controller;

import com.image.search.model.SearchRequest;
import com.image.search.model.entity.Image;
import com.image.search.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/search")
    public ResponseEntity<List<Image>> searchImages(SearchRequest searchRequest) {
        List<Image> result = imageService.search(searchRequest);
        return ResponseEntity.ok(result);
    }

}
