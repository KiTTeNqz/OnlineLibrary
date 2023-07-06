package com.example.onlinelibrary.mapper;

import com.example.onlinelibrary.model.getBookList.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getBookList.GetBooksListAdapterResponse;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalRequest;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalResponse;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetBooksListMapper {
    public GetBooksListExternalRequest mapRequest(GetBooksListAdapterRequest request) {
        GetBooksListExternalRequest externalRequest = new GetBooksListExternalRequest();
        List<GetBooksListExternalRequest.Attributes> searchData = request.getSearchAttributes().stream()
                .map(this::mapToAttributes)
                .collect(Collectors.toList());
        externalRequest.setBookData(searchData);
        return externalRequest;
    }

    public GetBooksListAdapterResponse mapResponse(GetBooksListExternalResponse response) {
        GetBooksListAdapterResponse adapterResponse = new GetBooksListAdapterResponse();
        List<GetBooksListAdapterResponse.Book> bookData = response.getExternalBookData().stream().
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

        List<GetBooksListAdapterResponse.Book.ContentData> contentData = externalBook.getExternalContentData().stream()
                .map(this::mapContentData)
                .toList();

        return new GetBooksListAdapterResponse.Book(
                externalBook.getId(),
                externalBook.getTitle(),
                mapCategory(externalBook.getCategory()),
                externalBook.getPublisher(),
                externalBook.getRenter(),
                rentalStartTime,
                rentalStopTime,
                externalBook.getRecommendationIdList(),
                contentData
        );
    }

    private GetBooksListAdapterResponse.Book.ContentData mapContentData(GetBooksListExternalResponse.ExternalBook.ExternalContentData externalContentData){
        return new GetBooksListAdapterResponse.Book.ContentData(
          externalContentData.getDescription(),
          externalContentData.getTitle(),
          externalContentData.getAuthor(),
          mapCategory(externalContentData.getCategory()),
          externalContentData.getRecommendationIdList()
        );
    }


    private GetBooksListExternalRequest.Attributes mapToAttributes
            (GetBooksListAdapterRequest.SearchAttribute searchAttribute) {
        return new GetBooksListExternalRequest.Attributes(
                searchAttributeToExternalAttributes(searchAttribute)
        );
    }

    private List<GetBooksListExternalRequest.Attribute> searchAttributeToExternalAttributes
            (GetBooksListAdapterRequest.SearchAttribute searchAttribute) {
        GetBooksListExternalRequest.Attribute externalAttribute = new GetBooksListExternalRequest.Attribute(
                searchAttribute.getAttribute(),
                searchAttribute.getValue(),
                mapType(searchAttribute.getType())
        );
        return List.of(externalAttribute);
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

    private int mapType(GetBooksListAdapterRequest.Type searchType) {
        switch (searchType) {
            case CONTAIN:
                return 0;
//            case EQUAL:
//                return 1;
            case NOT_EMPTY:
                return 3;
            case BETWEEN:
                return 4;
            default:
                return 1;
//                throw new ExceptionResponse("ERR-003", "error", "Ошибка обработки" +
//                        " создания/изменения внешнего объекта");
        }
    }
}
