package com.example.onlinelibrary.configuration;

import com.example.onlinelibrary.logger.LoggingInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(loggingInterceptor);


        return new RestTemplateBuilder()
                .messageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .setReadTimeout(Duration.ofMillis(20000))
                .interceptors(interceptors)
                .build();
    }
}
