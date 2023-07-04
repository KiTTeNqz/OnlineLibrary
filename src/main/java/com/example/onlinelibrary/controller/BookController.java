package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.AttrConversion.Attribute;
import com.example.onlinelibrary.model.AttrConversion.SearchAttribute;
import com.example.onlinelibrary.model.Book;
import com.example.onlinelibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<List<Book>> getBooksList(
            @RequestParam("x-trace-id") Long traceId,
            @RequestBody List<SearchAttribute> searchAttribute) throws ExceptionResponse {

            List<List<Attribute>> searchData = new ArrayList<>();
            try {
                searchData.add(searchAttribute.stream().
                        map(SearchAttribute::convertToAttribute).toList()
                );
            }catch (Exception e){
                throw new ExceptionResponse("ERR-003", "error", "Ошибка обработки" +
                        " создания/изменения внешнего объекта");
            }

            List<Book> books = bookService.getAllBooks(searchData, traceId);
            traceId++;
            return ResponseEntity.ok(books);
    }

}
