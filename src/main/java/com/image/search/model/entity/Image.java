package com.image.search.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Images")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "imagesCache")
public class Image {
    @Id
    private String id;
    private String author;
    private String camera;
    private String tags;
    @Column(name = "croppedPictureUrl")
    private String croppedPictureUrl;
    @Column(name = "fullPictureUrl")
    private String fullPictureUrl;
}
