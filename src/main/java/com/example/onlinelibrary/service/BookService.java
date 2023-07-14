package com.example.onlinelibrary.service;

import com.example.onlinelibrary.callers.BookCaller;
import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.mappers.GetBooksListMapper;
import com.example.onlinelibrary.mappers.UploadBookMapper;
import com.example.onlinelibrary.model.ContentData;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterResponse;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalResponse;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterResponse;
import com.example.onlinelibrary.model.uploadBook.UploadBookExternalRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookExternalResponse;
import com.example.onlinelibrary.validator.ContentDataValidator;
import com.example.onlinelibrary.validator.GetBooksListValidator;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookCaller bookCaller;

    public BookCaller getBookCaller() {
        return bookCaller;
    }

    private final GetBooksListMapper mapper;
    private final UploadBookMapper uploadMapper;

    public BookService(BookCaller bookCaller, GetBooksListMapper mapper, UploadBookMapper uploadMapper) {
        this.bookCaller = bookCaller;
        this.mapper = mapper;
        this.uploadMapper = uploadMapper;
    }

    public GetBooksListAdapterResponse getAllBooks(GetBooksListAdapterRequest adapterRequest, String traceId) throws ExceptionResponse {
        GetBooksListValidator.validate(adapterRequest);
        GetBooksListExternalRequest externalRequest = mapper.mapRequest(adapterRequest);
        GetBooksListExternalResponse externalResponse = bookCaller.callSystemGetBooksList(externalRequest, traceId);
        GetBooksListAdapterResponse adapterResponse = mapper.mapResponse(externalResponse);
        return adapterResponse;
    }

    public UploadBookAdapterResponse uploadBook(UploadBookAdapterRequest adapterRequest, String traceId) throws ExceptionResponse{
        for (ContentData contentData : adapterRequest.getBookData().getContentData()) {
            ContentDataValidator.validate(contentData);
        }
        UploadBookExternalRequest externalRequest = uploadMapper.mapRequest(adapterRequest);
        UploadBookExternalResponse externalResponse = bookCaller.callSystemUploadBook(externalRequest, traceId);
        UploadBookAdapterResponse adapterResponse = uploadMapper.mapResponse(externalResponse);
        return adapterResponse;
    }

}
