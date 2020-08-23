package com.image.search.config;

import com.image.search.filter.ClientInterceptor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@EnableCaching
@Configuration
public class SearchConfiguration {

    @Bean
    public RestTemplate restTemplate(ImageProperties imageProperties) {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();

        if (restTemplate.getInterceptors().isEmpty()) {
            interceptors = new ArrayList<>();
        }

        interceptors.add(new ClientInterceptor(imageProperties));
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

}
