package com.example.onlinelibrary.mappers;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.ContentData;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterResponse;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalResponse;
import com.example.onlinelibrary.model.getbookslist.inner.request.SearchAttribute;
import com.example.onlinelibrary.model.getbookslist.inner.response.ExternalBook;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetBooksListMapperTest {
    @Test
    public void createListAdapterRequest() throws ExceptionResponse {
        List<SearchAttribute> searchAttributes = new ArrayList<>();
        searchAttributes.add(new SearchAttribute("title", "book title", SearchAttribute.Type.EQUAL));
        searchAttributes.add(new SearchAttribute("title2", "book title2", SearchAttribute.Type.CONTAIN));
        searchAttributes.add(new SearchAttribute("title3", "book title3", SearchAttribute.Type.NOT_EMPTY));
        searchAttributes.add(new SearchAttribute("title4", "book title4", SearchAttribute.Type.BETWEEN));
        GetBooksListAdapterRequest request = new GetBooksListAdapterRequest(searchAttributes);
        GetBooksListMapper mapper = new GetBooksListMapper();
        GetBooksListExternalRequest externalRequest = mapper.mapRequest(request);

        assertEquals("title",externalRequest.getBookData().get(0).getAttributes().get(0).getAttribute(), "Неверный аттрибут");
        assertEquals("book title",externalRequest.getBookData().get(0).getAttributes().get(0).getValue(), "Неверное значение");
        assertEquals(2,externalRequest.getBookData().get(0).getAttributes().get(0).getType(), "Неверный тип");

        assertEquals("title2",externalRequest.getBookData().get(0).getAttributes().get(1).getAttribute(), "Неверный аттрибут");
        assertEquals("book title2",externalRequest.getBookData().get(0).getAttributes().get(1).getValue(), "Неверное значение");
        assertEquals(1,externalRequest.getBookData().get(0).getAttributes().get(1).getType(), "Неверный тип");
    }

    @Test
    public void createExternalResponse() {
        List<ExternalBook> externalBookData = new ArrayList<>();

        List<Long> recommendationIdList = new ArrayList<>();
        recommendationIdList.add(4L);
        recommendationIdList.add(5L);
        recommendationIdList.add(6L);

        List<ContentData> externalContentData = new ArrayList<>();
        externalContentData.add(new ContentData("Description", "Content Title", "Author", "poem", recommendationIdList));

        LocalDateTime rentalStartTime = LocalDateTime.of(2023, 1, 1, 10, 20,30);
        LocalDateTime rentalStopTime = LocalDateTime.of(2023, 1, 10, 10, 20,30);

        ExternalBook externalBook = new ExternalBook(123456L,"Renter", rentalStartTime, rentalStopTime,
                "Book Title", "Publisher", recommendationIdList, externalContentData);

        externalBookData.add(externalBook);
        GetBooksListExternalResponse externalResponse = new GetBooksListExternalResponse(externalBookData);
        GetBooksListMapper mapper = new GetBooksListMapper();
        GetBooksListAdapterResponse adapterResponse = mapper.mapResponse(externalResponse);

        assertEquals(1, adapterResponse.getCount(), "Неверное количество");
        assertEquals(123456L,adapterResponse.getBookData().get(0).getId(), "Неверный Id");
        assertEquals("Book Title",adapterResponse.getBookData().get(0).getTitle(), "Неверное название");
        assertEquals("Publisher",adapterResponse.getBookData().get(0).getPublisher(), "Неверный издатель");
        assertEquals("Renter",adapterResponse.getBookData().get(0).getRenter(), "Неверный renter");
        assertEquals(ZonedDateTime.of(2023,1,1,8,20,30,0, ZoneId.of("+03:00")),adapterResponse.getBookData().get(0).getRentalStartTime(), "Неверное время начала аренды");
        assertEquals(ZonedDateTime.of(2023,1,10,8,20,30,0, ZoneId.of("+03:00")),adapterResponse.getBookData().get(0).getRentalStopTime(), "Неверное время конца аренды");
        assertEquals("Стихотворение",adapterResponse.getBookData().get(0).getExternalContentData().get(0).getCategory());
    }
}
