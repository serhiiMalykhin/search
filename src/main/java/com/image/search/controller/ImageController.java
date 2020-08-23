package com.image.search.controller;

import com.image.search.model.dto.SearchRequest;
import com.image.search.model.entity.Image;
import com.image.search.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/search")
    public ResponseEntity<List<Image>> searchImages(SearchRequest searchRequest) {
        List<Image> result = imageService.search(searchRequest);
        return ResponseEntity.ok(result);
    }

}
