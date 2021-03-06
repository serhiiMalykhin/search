package com.image.search.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.image.search.model.dto.Picture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagePaginationResult {
    private List<Picture> pictures;
    private Integer page;
    private Integer pageCount;
    @JsonProperty("hasMore")
    private Boolean hasMorePages;
}
