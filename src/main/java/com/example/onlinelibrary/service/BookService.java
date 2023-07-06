package com.example.onlinelibrary.service;

import com.example.onlinelibrary.callers.BookCaller;
import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.mapper.GetBooksListMapper;
import com.example.onlinelibrary.model.getBookList.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getBookList.GetBooksListAdapterResponse;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalRequest;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalResponse;
import com.example.onlinelibrary.validator.GetBookListValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

@Service
public class BookService {

    private final RestTemplate restTemplate;
    private final BookCaller bookCaller;
    private final GetBooksListMapper mapper;

    public BookService(RestTemplate restTemplate, BookCaller bookCaller, GetBooksListMapper mapper) {
        this.restTemplate = restTemplate;
        this.bookCaller = bookCaller;
        this.mapper = mapper;
    }

    public GetBooksListAdapterResponse getAllBooks(GetBooksListAdapterRequest adapterRequest, String traceId) throws ExceptionResponse {
        GetBookListValidator.validate(adapterRequest);
        GetBooksListExternalRequest externalRequest = mapper.mapRequest(adapterRequest);
        GetBooksListExternalResponse externalResponse = bookCaller.callExternalSystem(externalRequest, traceId);
        GetBooksListAdapterResponse adapterResponse = mapper.mapResponse(externalResponse);
        return adapterResponse;
    }

}
