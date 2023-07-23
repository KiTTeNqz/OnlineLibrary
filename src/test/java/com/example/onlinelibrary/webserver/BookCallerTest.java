package com.example.onlinelibrary.webserver;

import com.example.onlinelibrary.callers.BookCaller;
import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.mappers.GetBooksListMapper;
import com.example.onlinelibrary.mappers.UploadBookMapper;
import com.example.onlinelibrary.model.ContentData;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListExternalResponse;
import com.example.onlinelibrary.model.getbookslist.inner.request.SearchAttribute;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookExternalRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookExternalResponse;
import com.example.onlinelibrary.model.uploadBook.inner.BookData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
public class BookCallerTest {

    @Autowired
    private BookCaller bookCaller;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void mockGetBooksList() throws URISyntaxException, ExceptionResponse, IOException {

        File file = new File("src/test/java/resources/getBooksListExternalResponse.json");

        List<SearchAttribute> searchAttributes = new ArrayList<>();
        searchAttributes.add(new SearchAttribute("title", "book title", SearchAttribute.Type.EQUAL));
        searchAttributes.add(new SearchAttribute("title2", "book title2", SearchAttribute.Type.CONTAIN));
        searchAttributes.add(new SearchAttribute("title3", "book title3", SearchAttribute.Type.NOT_EMPTY));
        searchAttributes.add(new SearchAttribute("title4", "book title4", SearchAttribute.Type.BETWEEN));
        GetBooksListAdapterRequest request = new GetBooksListAdapterRequest(searchAttributes);
        GetBooksListMapper mapper = new GetBooksListMapper();
        GetBooksListExternalRequest externalRequest = mapper.mapRequest(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-trace-id", "123UFC");

        GetBooksListExternalResponse externalResponse = objectMapper.readValue(file, GetBooksListExternalResponse.class);


        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:8080/getBooksList")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header("Content-type", "application/json"))
                .andExpect(header("x-trace-id", "123UFC"))

                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\n" +
                                "  \"bookData\": [\n" +
                                "    {\n" +
                                "      \"id\": 122334567,\n" +
                                "      \"renter\": \"ФИО\",\n" +
                                "      \"rentalStartTime\": \"23.06.2022 17:42:36\",\n" +
                                "      \"rentalStopTime\": \"25.06.2022 07:41:30\",\n" +
                                "      \"title\": \"Сборник стихов\",\n" +
                                "      \"publisher\": \"Издательство\",\n" +
                                "      \"recommendationIdList\": [123, 8347843, 6543893],\n" +
                                "      \"contentData\": [\n" +
                                "        {\n" +
                                "          \"description\": \"Стих про зиму\",\n" +
                                "          \"title\": \"Зимнее утро\",\n" +
                                "          \"author\": \"Пушкин А.С.\",\n" +
                                "          \"category\": \"poem\",\n" +
                                "          \"recommendationIdList\": [311749, 1234, 54323]\n" +
                                "        }\n" +
                                "      ]\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}")
                );

        GetBooksListExternalResponse actualResponse = bookCaller.callSystemGetBooksList(externalRequest, "123UFC");
        Assertions.assertEquals(externalResponse.getBookData().get(0).getId(), actualResponse.getBookData().get(0).getId());
        mockServer.verify();
    }

    @Test
    public void mockUploadBook() throws URISyntaxException, ExceptionResponse, IOException {

        File file = new File("src/test/java/resources/uploadBookExternalResponse.json");

        List<Long> recommendationIdList = new ArrayList<>();
        recommendationIdList.add(4L);
        recommendationIdList.add(5L);
        recommendationIdList.add(6L);

        List<ContentData> externalContentData = new ArrayList<>();
        externalContentData.add(new ContentData("Description", "Content Title", "Author", "poem", recommendationIdList));

        UploadBookAdapterRequest adapterRequest = new UploadBookAdapterRequest(new BookData("title", "book publisher", externalContentData));
        UploadBookMapper mapper = new UploadBookMapper();
        UploadBookExternalRequest externalRequest = mapper.mapRequest(adapterRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-trace-id", "123UFC");

        UploadBookExternalResponse externalResponse = objectMapper.readValue(file, UploadBookExternalResponse.class);


        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://localhost:8080/uploadBook")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header("Content-type", "application/json"))
                .andExpect(header("x-trace-id", "123UFC"))

                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\n" +
                                "  \"status_code\": 0,\n" +
                                "  \"id\": 122334567,\n" +
                                "  \"date_of_upload\": \"23.06.2023 20:22:36\"\n" +
                                "}")
                );


        UploadBookExternalResponse actualResponse = bookCaller.callSystemUploadBook(externalRequest, "123UFC");
        Assertions.assertEquals(externalResponse.getStatus_code(), actualResponse.getStatus_code());
        mockServer.verify();
    }
}
