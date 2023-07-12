package com.example.onlinelibrary.callers;

import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalResponse;
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
        File file = new File("src/test/java/com/example/onlinelibrary/resources/adapterRequest.json");

        GetBooksListAdapterRequest adapterRequest = objectMapper.readValue(file, GetBooksListAdapterRequest.class);

        assertThat(adapterRequest.getSearchAttributes().get(0).getAttribute()).isEqualTo("author");
        assertThat(adapterRequest.getSearchAttributes().get(0).getValue()).isEqualTo("John Doe");
        assertThat(adapterRequest.getSearchAttributes().get(0).getType()).isEqualTo(GetBooksListAdapterRequest.Type.EQUAL);
    }

    @Test
    void jsonFileToResponse() throws IOException {
        File file = new File("src/test/java/com/example/onlinelibrary/resources/externalResponse.json");

        GetBooksListExternalResponse externalResponse = objectMapper.readValue(file, GetBooksListExternalResponse.class);

        assertThat(externalResponse.getBookData().get(0).getRenter()).isEqualTo("ФИО");
        assertThat(externalResponse.getBookData().get(0).getPublisher()).isEqualTo("Издательство");

    }
}
