package com.image.search.model.converter;

import com.image.search.model.dto.ImageDto;
import com.image.search.model.entity.Image;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter implements Converter<ImageDto, Image> {

    @Override
    public Image convert(ImageDto imageDto) {
        return Image.builder()
                .id(imageDto.getId())
                .tags(imageDto.getTags())
                .camera(imageDto.getCamera())
                .author(imageDto.getAuthor())
                .croppedPictureUrl(imageDto.getCroppedPicture())
                .fullPictureUrl(imageDto.getFullPicture())
                .build();
    }
}
