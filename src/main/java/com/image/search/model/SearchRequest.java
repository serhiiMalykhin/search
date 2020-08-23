package com.image.search.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchRequest {
    private String id;
    private String author;
    private String camera;
    private String tags;
    private String croppedPictureUrl;
    private String fullPictureUrl;
}
