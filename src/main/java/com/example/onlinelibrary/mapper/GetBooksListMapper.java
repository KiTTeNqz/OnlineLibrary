package com.example.onlinelibrary.mapper;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.ContentData;
import com.example.onlinelibrary.model.getBookList.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getBookList.GetBooksListAdapterResponse;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalRequest;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalRequest.Attributes;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalResponse;
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
        List<GetBooksListAdapterResponse.Book> bookData = response.getBookData().stream().
                map(this::mapToResponseBook)
                .collect(Collectors.toList());
        adapterResponse.setBookData(bookData);
        adapterResponse.setCount(adapterResponse.getBookData().size());
        return adapterResponse;
    }

    private GetBooksListAdapterResponse.Book mapToResponseBook(GetBooksListExternalResponse.ExternalBook externalBook) {
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
                .map(this::mapContentData)
                .toList();

        return new GetBooksListAdapterResponse.Book(
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

    private ContentData mapContentData(ContentData externalContentData){
        return new ContentData(
          externalContentData.getDescription(),
          externalContentData.getTitle(),
          externalContentData.getAuthor(),
          mapCategory(externalContentData.getCategory()),
          externalContentData.getRecommendationIdList()
        );
    }


    private Attributes mapToAttributes
            (List<GetBooksListAdapterRequest.SearchAttribute> searchAttributes) throws ExceptionResponse {
        List<GetBooksListExternalRequest.Attribute> attributeList = searchAttributes.stream()
                .map(searchAttribute -> {
                    try {
                        return searchAttributeToExternalAttribute(searchAttribute);
                    } catch (ExceptionResponse e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
        return new Attributes(attributeList);
    }

    private GetBooksListExternalRequest.Attribute searchAttributeToExternalAttribute
            (GetBooksListAdapterRequest.SearchAttribute searchAttribute) throws ExceptionResponse {
        return new GetBooksListExternalRequest.Attribute(
                searchAttribute.getAttribute(),
                searchAttribute.getValue(),
                mapType(searchAttribute.getType())
        );
    }

    private String mapCategory(String category) {
        return categoryMapping.getOrDefault(category, category);
    }

    private int mapType(GetBooksListAdapterRequest.Type searchType) throws ExceptionResponse {
        switch (searchType) {
            case CONTAIN:
                return 1;
            case EQUAL:
                return 2;
            case NOT_EMPTY:
                return 3;
            case BETWEEN:
                return 4;
            default:
                throw new ExceptionResponse("ERR-002", "error", "Ошибка при валидации запроса");
        }
    }
}
