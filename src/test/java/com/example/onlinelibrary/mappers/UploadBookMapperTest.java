package com.example.onlinelibrary.mappers;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.ContentData;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterResponse;
import com.example.onlinelibrary.model.uploadBook.UploadBookExternalRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookExternalResponse;
import com.example.onlinelibrary.model.uploadBook.inner.BookData;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UploadBookMapperTest {
    @Test
    public void createUploadExternalRequest() throws ExceptionResponse {

        List<Long> recommendationIdList = new ArrayList<>();
        recommendationIdList.add(4L);
        recommendationIdList.add(5L);
        recommendationIdList.add(6L);

        List<ContentData> externalContentData = new ArrayList<>();
        externalContentData.add(new ContentData("Description", "Content Title", "Author", "poem", recommendationIdList));

        UploadBookAdapterRequest adapterRequest = new UploadBookAdapterRequest(new BookData("title", "book publisher", externalContentData));
        UploadBookMapper mapper = new UploadBookMapper();
        UploadBookExternalRequest externalRequest = mapper.mapRequest(adapterRequest);

        assertEquals("title",externalRequest.getTitle(), "Неверное название");
        assertEquals("book publisher",externalRequest.getPublisher(), "Неверный издатель");
        assertEquals("Author",externalRequest.getContentData().get(0).getAuthor(), "Неверный автор");
    }

    @Test
    public void createUploadAdapterResponse() throws ExceptionResponse{
        Integer statusCode = 0;
        Long id = 12345L;
        LocalDateTime dateOfUpload = LocalDateTime.now();
        String err = "";

        UploadBookExternalResponse externalResponse = new UploadBookExternalResponse(statusCode, id, dateOfUpload, err);

        UploadBookMapper mapper = new UploadBookMapper();

        UploadBookAdapterResponse successResponse = mapper.mapResponse(externalResponse);
        assertEquals("SUCCESS", successResponse.getStatus());
        assertEquals(id, successResponse.getId());
    }


}
