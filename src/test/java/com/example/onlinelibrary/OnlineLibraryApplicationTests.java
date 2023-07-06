package com.example.onlinelibrary;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.mapper.GetBooksListMapper;
import com.example.onlinelibrary.model.getBookList.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getBookList.GetBooksListAdapterResponse;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalRequest;
import com.example.onlinelibrary.model.getBookList.GetBooksListExternalResponse;
import com.example.onlinelibrary.validator.GetBookListValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OnlineLibraryApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void createListAdapterRequest() {
        List<GetBooksListAdapterRequest.SearchAttribute> searchAttributes = new ArrayList<>();
        searchAttributes.add(new GetBooksListAdapterRequest.SearchAttribute("title", "book title", GetBooksListAdapterRequest.Type.EQUAL));
        GetBooksListAdapterRequest request = new GetBooksListAdapterRequest(searchAttributes);
        GetBooksListMapper mapper = new GetBooksListMapper();
        GetBooksListExternalRequest externalRequest = mapper.mapRequest(request);
        assertEquals("title",externalRequest.getBookData().get(0).getAttributes().get(0).getAttribute(), "Неверный аттрибут");
        assertEquals("book title",externalRequest.getBookData().get(0).getAttributes().get(0).getValue(), "Неверное значение");
        assertEquals(1,externalRequest.getBookData().get(0).getAttributes().get(0).getType(), "Неверный тип");
    }

    @Test
    public void createExternalResponse() {
        List<GetBooksListExternalResponse.ExternalBook> externalBookData = new ArrayList<>();

        List<Long> recommendationIdList = new ArrayList<>();
        recommendationIdList.add(4L);
        recommendationIdList.add(5L);
        recommendationIdList.add(6L);

        List<GetBooksListExternalResponse.ExternalBook.ExternalContentData> externalContentData = new ArrayList<>();
        externalContentData.add(new GetBooksListExternalResponse.ExternalBook.ExternalContentData("Description", "Content Title", "Author", "poem", recommendationIdList));

        LocalDateTime rentalStartTime = LocalDateTime.of(2023, 1, 1, 10, 20,30);
        LocalDateTime rentalStopTime = LocalDateTime.of(2023, 1, 10, 10, 20,30);

        GetBooksListExternalResponse.ExternalBook externalBook = new GetBooksListExternalResponse.ExternalBook(123456L, "Book Title", "Publisher", "Renter", rentalStartTime, rentalStopTime, recommendationIdList, externalContentData);

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

    @Test
    public void validateAdapterRequest() throws ExceptionResponse {
        List<GetBooksListAdapterRequest.SearchAttribute> searchAttributes = new ArrayList<>();
        searchAttributes.add(new GetBooksListAdapterRequest.SearchAttribute("title", "book title", GetBooksListAdapterRequest.Type.EQUAL));
        GetBooksListAdapterRequest request = new GetBooksListAdapterRequest(searchAttributes);
        GetBookListValidator validator = new GetBookListValidator();
        assertEquals(true,validator.validate(request),"Ошибка валидации");
    }

}
