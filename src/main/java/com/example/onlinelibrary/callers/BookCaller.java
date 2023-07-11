package com.example.onlinelibrary.callers;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalRequest;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookCaller {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public BookCaller() {
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        this.restTemplate = new RestTemplateBuilder()
                .messageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    public GetBooksListExternalResponse callExternalSystem(GetBooksListExternalRequest request, String traceId) throws ExceptionResponse {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-trace-id", String.valueOf(traceId));

        HttpEntity<GetBooksListExternalRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<GetBooksListExternalResponse> responseEntity;

            responseEntity = restTemplate.postForEntity(
                    "http://localhost:8080/getBooksList",
                    requestEntity,
                    GetBooksListExternalResponse.class
            );

        return responseEntity.getBody();
    }
}
