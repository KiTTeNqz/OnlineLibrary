package com.example.onlinelibrary.service;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.AttrConversion.Attribute;
import com.example.onlinelibrary.model.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final RestTemplate restTemplate;

    public BookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Book> getAllBooks(List<List<Attribute>> searchData, Long traceId) throws ExceptionResponse {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-trace-id", String.valueOf(traceId));

        HttpEntity<List<List<Attribute>>> requestEntity;
            try {
                requestEntity = new HttpEntity<>(searchData, headers);
            }
            catch (Exception e){
                throw new ExceptionResponse("ERR-002", "error", "Ошибка при валидации запроса");
            }

            ResponseEntity<Book[]> responseEntity;

            responseEntity = restTemplate.postForEntity(
                    "http://localhost:8080/content/search",
                    requestEntity,
                    Book[].class
            );

        Book[] books;
        try {
            books = responseEntity.getBody();
        }catch (Exception e){
            throw new ExceptionResponse("ERR-004", "error", "Бизнес-ошибка, " +
                    "произошедшая внутри системы-получателя при выполнении операции");
        }


        if(books!=null){
            try {
                return Arrays.stream(books).map(this::mapResponse).collect(Collectors.toList());
            }
            catch (Exception e){
                throw new ExceptionResponse("ERR-003", "error", "Ошибка обработки" +
                        " создания/изменения внешнего объекта");
            }
        }
        else return Collections.emptyList();
    }

    private Book mapResponse(Book item) {
        item.setCategory(mapCategory(item.getCategory()));

        item.setRentalStartTime(mapDateTime(item.getRentalStartTime()));

        return item;
    }

    private String mapCategory(String category) {
        Map<String, String> categoryMapping = new HashMap<>();
        categoryMapping.put("novel", "Роман");
        categoryMapping.put("story", "Рассказ");
        categoryMapping.put("poem", "Стихотворение");

        if (categoryMapping.containsKey(category)) {
            return categoryMapping.get(category);
        }
        return category;
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime mapDateTime(ZonedDateTime dateTime) {
        ZoneId toZone = ZoneId.of("+03:00");

        return dateTime.withZoneSameInstant(toZone);
    }
}
