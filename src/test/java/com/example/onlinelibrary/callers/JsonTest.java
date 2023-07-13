package com.example.onlinelibrary.callers;

import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalResponse;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookExternalResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonTest {

    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    void jsonFileToObj() throws IOException {
        File file = new File("src/test/java/com/example/onlinelibrary/resources/getBooksListAdapterRequest.json");
        GetBooksListAdapterRequest adapterRequest = objectMapper.readValue(file, GetBooksListAdapterRequest.class);
        assertThat(adapterRequest.getSearchAttributes().get(0).getAttribute()).isEqualTo("author");
        assertThat(adapterRequest.getSearchAttributes().get(0).getValue()).isEqualTo("John Doe");
        assertThat(adapterRequest.getSearchAttributes().get(0).getType()).isEqualTo(GetBooksListAdapterRequest.Type.EQUAL);
    }

    @Test
    void getBooksJsonToResponse() throws IOException {
        File file = new File("src/test/java/com/example/onlinelibrary/resources/getBooksListExternalResponse.json");
        GetBooksListExternalResponse externalResponse = objectMapper.readValue(file, GetBooksListExternalResponse.class);
        assertThat(externalResponse.getBookData().get(0).getRenter()).isEqualTo("ФИО");
        assertThat(externalResponse.getBookData().get(0).getPublisher()).isEqualTo("Издательство");

    }
    @Test
    void uploadBookJsonToResponse() throws IOException {
        File file = new File("src/test/java/com/example/onlinelibrary/resources/uploadBookAdapterRequest.json");
        UploadBookAdapterRequest adapterRequest = objectMapper.readValue(file, UploadBookAdapterRequest.class);
        assertThat(adapterRequest.getBookData().getTitle()).isEqualTo("Сборник стихов");
        assertThat(adapterRequest.getBookData().getPublisher()).isEqualTo("Издательство");

    }

    @Test
    void uploadBookJsonToRequest() throws IOException {
        File file = new File("src/test/java/com/example/onlinelibrary/resources/uploadBookExternalResponse.json");
        UploadBookExternalResponse externalResponse = objectMapper.readValue(file, UploadBookExternalResponse.class);
        assertThat(externalResponse.getStatus_code()).isEqualTo(0);
        assertThat(externalResponse.getId()).isEqualTo(122334567);

    }

}
