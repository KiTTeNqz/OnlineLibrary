package com.example.onlinelibrary.service;

import com.example.onlinelibrary.callers.BookCaller;
import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.mappers.GetBooksListMapper;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterResponse;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalResponse;
import com.example.onlinelibrary.validator.GetBooksListValidator;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookCaller bookCaller;

    public BookCaller getBookCaller() {
        return bookCaller;
    }

    private final GetBooksListMapper mapper;

    public BookService(BookCaller bookCaller, GetBooksListMapper mapper) {
        this.bookCaller = bookCaller;
        this.mapper = mapper;
    }

    public GetBooksListAdapterResponse getAllBooks(GetBooksListAdapterRequest adapterRequest, String traceId) throws ExceptionResponse {
        GetBooksListValidator.validate(adapterRequest);
        GetBooksListExternalRequest externalRequest = mapper.mapRequest(adapterRequest);
        GetBooksListExternalResponse externalResponse = bookCaller.callExternalSystem(externalRequest, traceId);
        GetBooksListAdapterResponse adapterResponse = mapper.mapResponse(externalResponse);
        return adapterResponse;
    }

}
