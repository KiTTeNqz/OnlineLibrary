package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.getBookList.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getBookList.GetBooksListAdapterResponse;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterResponse;
import com.example.onlinelibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping ("/getBooksList")
    public ResponseEntity<GetBooksListAdapterResponse> getBooksList(
            @RequestHeader("x-trace-id") String traceId,
            @RequestBody GetBooksListAdapterRequest request) throws ExceptionResponse {
            GetBooksListAdapterResponse response = bookService.getAllBooks(request, traceId);
            return ResponseEntity.ok(response);
    }
//
//    @PostMapping("/uploadBook")
//    public ResponseEntity<UploadBookAdapterRequest> uploadBook(
//            @RequestHeader("x-trace-id") String traceId,
//            @RequestBody UploadBookAdapterRequest request) throws ExceptionResponse {
//        UploadBookAdapterResponse response = bookService.uploadBook(request, traceId);
//        return ResponseEntity.ok(response);
//    }

}
