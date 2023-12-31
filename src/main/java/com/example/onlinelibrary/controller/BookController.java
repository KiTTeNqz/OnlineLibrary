package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterResponse;
import com.example.onlinelibrary.model.updaterecommendation.UpdateRecommendationAdapterRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterResponse;
import com.example.onlinelibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            @RequestHeader(value = "x-trace-id", required = false) String traceId,
            @RequestBody GetBooksListAdapterRequest request) throws ExceptionResponse {
            GetBooksListAdapterResponse response = bookService.getAllBooks(request, traceId);
            return ResponseEntity.ok(response);
    }

    @PostMapping("/uploadBook")
    public ResponseEntity<UploadBookAdapterResponse> uploadBook(
            @RequestHeader(value = "x-trace-id", required = false) String traceId,
            @RequestBody UploadBookAdapterRequest request) throws ExceptionResponse{
        UploadBookAdapterResponse response = bookService.uploadBook(request, traceId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/updateRecommendation")
    public ResponseEntity<String> updateRecommendation(
            @RequestHeader(value = "x-trace-id", required = false) String traceId,
            @RequestBody UpdateRecommendationAdapterRequest request) throws ExceptionResponse{
        bookService.updateRecommendation(request, traceId);
        return ResponseEntity.ok().build();
    }

}
