package com.image.search.config;

import com.image.search.filter.ClientInterceptor;
import com.image.search.model.AuthToken;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EnableCaching
@Configuration
public class SearchConfiguration {

    @Bean
    public RestTemplate restTemplate(AuthToken authToken) {
        return getRestTemplate(authToken);
    }

    @Bean
    public AuthToken token(ImageProperties imageProperties) {
        return new RestTemplate().postForObject(imageProperties.getBase() + imageProperties.getAuth(),
                Collections.singletonMap("apiKey", imageProperties.getApiKey()), AuthToken.class);
    }

    private RestTemplate getRestTemplate(AuthToken authToken) {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();

        if (restTemplate.getInterceptors().isEmpty()) {
            interceptors = new ArrayList<>();
        }

        interceptors.add(new ClientInterceptor(authToken));
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }
}
