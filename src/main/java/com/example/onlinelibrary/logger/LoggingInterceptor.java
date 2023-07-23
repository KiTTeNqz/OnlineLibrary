package com.example.onlinelibrary.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    private final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logger.info("Request: {} {} {}", request.getMethod(), request.getURI(), request.getHeaders());

        if (body.length > 0) {
            logger.info("Request Body: {}", new String(body, StandardCharsets.UTF_8));
        }

        ClientHttpResponse response = execution.execute(request, body);

        logger.info("Response Status: {}", response.getStatusCode());
        logger.info("Response Headers: {}", response.getHeaders());


        return response;
    }
}