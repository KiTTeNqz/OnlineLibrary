package com.example.onlinelibrary.callers;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalResponse;
import com.example.onlinelibrary.model.uploadBook.UploadBookExternalRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookExternalResponse;
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

    public GetBooksListExternalResponse callSystemGetBooksList(GetBooksListExternalRequest request, String traceId) throws ExceptionResponse {
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

    public UploadBookExternalResponse callSystemUploadBook(UploadBookExternalRequest request, String traceId) throws ExceptionResponse {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-trace-id", String.valueOf(traceId));

        HttpEntity<UploadBookExternalRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<UploadBookExternalResponse> responseEntity;

        responseEntity = restTemplate.postForEntity(
                "http://localhost:8080/uploadBook",
                requestEntity,
                UploadBookExternalResponse.class
        );

        return responseEntity.getBody();
    }
}
