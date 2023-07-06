package com.example.onlinelibrary.callers;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalRequest;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookCaller {

    private final RestTemplate restTemplate;

    public BookCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GetBooksListExternalResponse callExternalSystem(GetBooksListExternalRequest request, String traceId) throws ExceptionResponse {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-trace-id", String.valueOf(traceId));

        HttpEntity<GetBooksListExternalRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<GetBooksListExternalResponse> responseEntity;

        try {
            responseEntity = restTemplate.postForEntity(
                    "http://localhost:8080/OnlineLibrary/search",
                    requestEntity,
                    GetBooksListExternalResponse.class
            );
        } catch (Exception e) {
            throw new ExceptionResponse("ERR-004", "error", "Бизнес-ошибка, " +
                    "произошедшая внутри системы-получателя при выполнении операции");
        }

        return responseEntity.getBody();
    }
}
