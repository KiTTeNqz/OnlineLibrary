package com.example.onlinelibrary.mappers;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.ContentData;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterResponse;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalRequest;
import com.example.onlinelibrary.model.getbookslist.inner.request.Attribute;
import com.example.onlinelibrary.model.getbookslist.inner.request.Attributes;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalResponse;
import com.example.onlinelibrary.model.getbookslist.inner.request.SearchAttribute;
import com.example.onlinelibrary.model.getbookslist.inner.response.Book;
import com.example.onlinelibrary.model.getbookslist.inner.response.ExternalBook;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GetBooksListMapper {
    private final Map<String, String> categoryMapping;

    public GetBooksListMapper() {
        categoryMapping = new HashMap<>();
        categoryMapping.put("novel", "Роман");
        categoryMapping.put("story", "Рассказ");
        categoryMapping.put("poem", "Стихотворение");
    }
    public GetBooksListExternalRequest mapRequest(GetBooksListAdapterRequest request) throws ExceptionResponse {
        GetBooksListExternalRequest externalRequest = new GetBooksListExternalRequest();
        List<Attributes> bookData = new ArrayList<>();
        Attributes attributes = mapToAttributes(request.getSearchAttributes());
        bookData.add(attributes);
        externalRequest.setBookData(bookData);
        return externalRequest;
    }

    public GetBooksListAdapterResponse mapResponse(GetBooksListExternalResponse response) {
        GetBooksListAdapterResponse adapterResponse = new GetBooksListAdapterResponse();
        List<Book> bookData = response.getBookData().stream().
                map(this::mapToResponseBook)
                .collect(Collectors.toList());
        adapterResponse.setBookData(bookData);
        adapterResponse.setCount(adapterResponse.getBookData().size());
        return adapterResponse;
    }

    private Book mapToResponseBook(ExternalBook externalBook) {
        // Конвертация LocalDateTime в ZonedDateTime
        ZonedDateTime rentalStartTime = ZonedDateTime.of(
                        externalBook.getRentalStartTime(),
                        ZoneOffset.ofHours(5))
                .withZoneSameInstant(ZoneId.of("+03:00"));

        ZonedDateTime rentalStopTime = ZonedDateTime.of(
                        externalBook.getRentalStopTime(),
                        ZoneOffset.ofHours(5))
                .withZoneSameInstant(ZoneId.of("+03:00"));

        List<ContentData> contentData = externalBook.getContentData().stream()
                .peek(content -> content.setCategory(mapCategory(content.getCategory())))
                .toList();

        return new Book(
                externalBook.getId(),
                externalBook.getTitle(),
                externalBook.getPublisher(),
                externalBook.getRenter(),
                rentalStartTime,
                rentalStopTime,
                externalBook.getRecommendationIdList(),
                contentData
        );
    }

    private Attributes mapToAttributes
            (List<SearchAttribute> searchAttributes) throws ExceptionResponse {
        List<Attribute> attributeList = new ArrayList<>();
        for (SearchAttribute searchAttribute : searchAttributes) {
            Attribute attribute = searchAttributeToExternalAttribute(searchAttribute);
            attributeList.add(attribute);
        }
        return new Attributes(attributeList);
    }

    private Attribute searchAttributeToExternalAttribute
            (SearchAttribute searchAttribute) throws ExceptionResponse {
        return new Attribute(
                searchAttribute.getAttribute(),
                searchAttribute.getValue(),
                mapType(searchAttribute.getType())
        );
    }

    private String mapCategory(String category) {
        return categoryMapping.getOrDefault(category, category);
    }

    private int mapType(SearchAttribute.Type searchType) throws ExceptionResponse {
        switch (searchType) {
            case CONTAIN:
                return 1;
            case EQUAL:
                return 2;
            case NOT_EMPTY:
                return 3;
            case BETWEEN:
                return 4;
        }
        return 0;
    }
}
