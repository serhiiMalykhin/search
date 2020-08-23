package com.image.search.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Picture {
    private String id;
    @JsonProperty("cropped_picture")
    private String url;
}
