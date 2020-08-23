package com.image.search.filter;

import com.image.search.model.AuthToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class ClientInterceptor implements ClientHttpRequestInterceptor {

    private AuthToken authToken;

    public ClientInterceptor(AuthToken authToken) {
        if (authToken.getAuth() != null && !authToken.getAuth()) {
            throw new IllegalArgumentException("Please, provide valid token");
        }
        this.authToken = authToken;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        request.getHeaders().add("Authorization", "Bearer " + authToken.getToken());

        return execution.execute(request, body);
    }

}
