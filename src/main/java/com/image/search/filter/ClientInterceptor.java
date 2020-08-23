package com.image.search.filter;

import com.image.search.config.ImageProperties;
import com.image.search.model.dto.AuthToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

@Slf4j
public class ClientInterceptor implements ClientHttpRequestInterceptor {

    private ImageProperties imageProperties;

    private AuthToken authToken;

    public ClientInterceptor(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
        authToken = getAuthToken();
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        try {
            setToken(request);
            return execution.execute(request, body);
        } catch (HttpClientErrorException e) {
            authToken = getAuthToken();
            setToken(request);
            return execution.execute(request, body);
        }
    }

    private void setToken(HttpRequest request) {
        request.getHeaders().add("Authorization", "Bearer " + authToken.getToken());
    }

    private AuthToken getAuthToken() {
        return new RestTemplate().postForObject(imageProperties.getBase() + imageProperties.getAuth(),
                Collections.singletonMap("apiKey", imageProperties.getApiKey()), AuthToken.class);
    }

}
