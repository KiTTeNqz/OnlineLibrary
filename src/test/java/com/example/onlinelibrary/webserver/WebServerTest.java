package com.example.onlinelibrary.webserver;

import com.example.onlinelibrary.callers.BookCaller;
import com.example.onlinelibrary.controller.BookController;
import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.mappers.GetBooksListMapper;
import com.example.onlinelibrary.mappers.UploadBookMapper;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterResponse;
import com.example.onlinelibrary.service.BookService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest
public class WebServerTest {
    private MockWebServer mockWebServer;

//    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(WebServerTest.class);


    @BeforeEach
    public void setUp() throws IOException{
        mockWebServer = new MockWebServer();
        mockWebServer.start(8080);
        logger.debug("Started MockWebServer");
    }

    @AfterEach
    public void tearDown() throws IOException{
        mockWebServer.shutdown();
        logger.debug("Shutting down MockWebServer");
    }

    @Test
    public void testMyAdapter() throws InterruptedException, ExceptionResponse {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("{\n" +
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
                        "}");

        mockWebServer.enqueue(mockResponse);
        logger.debug("Created MockResponse");
        BookController bookController = new BookController(new BookService(new BookCaller(restTemplate), new GetBooksListMapper(), new UploadBookMapper()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-trace-id", "123UFC");
        List<GetBooksListAdapterRequest.SearchAttribute> searchAttributes = new ArrayList<>();
        searchAttributes.add(new GetBooksListAdapterRequest.SearchAttribute("title", "book title", GetBooksListAdapterRequest.Type.EQUAL));
        searchAttributes.add(new GetBooksListAdapterRequest.SearchAttribute("title2", "book title2", GetBooksListAdapterRequest.Type.CONTAIN));
        searchAttributes.add(new GetBooksListAdapterRequest.SearchAttribute("title3", "book title3", GetBooksListAdapterRequest.Type.NOT_EMPTY));
        searchAttributes.add(new GetBooksListAdapterRequest.SearchAttribute("title4", "book title4", GetBooksListAdapterRequest.Type.BETWEEN));
        GetBooksListAdapterRequest request = new GetBooksListAdapterRequest(searchAttributes);
        ResponseEntity<GetBooksListAdapterResponse> response = bookController.getBooksList(headers.get("x-trace-id").toString(), request);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("application/json", response.getHeaders().getContentType().toString());
        GetBooksListAdapterResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        okhttp3.mockwebserver.RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("POST", recordedRequest.getMethod());
        assertEquals("/getBooksList", recordedRequest.getPath());
        assertEquals("123UFC", recordedRequest.getHeader("x-trace-id"));
        assertEquals(0, mockWebServer.getRequestCount());
    }
}
