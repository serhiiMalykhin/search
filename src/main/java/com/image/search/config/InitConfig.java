package com.image.search.config;

import com.image.search.model.dto.Picture;
import com.image.search.rest.ImageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class InitConfig {

    private final ImageClient imageClient;

    @PostConstruct
    void init() {
        fetchImages();
    }

    /**
     * executes every hour
     */
    @Scheduled(cron = "0 * */1 ? * *")
    public void fetchImages() {
        new Thread(() -> {
            List<Picture> result = imageClient.fetchPictures();
            imageClient.fetchImages(result);
        }).start();
    }

}
