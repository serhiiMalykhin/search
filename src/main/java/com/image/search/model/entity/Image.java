package com.image.search.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Images")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "imagesCache")
public class Image {
    @Id
    private String id;
    private String author;
    private String camera;
    private String tags;
    @JsonProperty("cropped_picture")
    private String croppedPictureUrl;
    @JsonProperty("full_picture")
    private String fullPictureUrl;
}
