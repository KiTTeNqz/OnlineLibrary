package com.example.onlinelibrary.validator;

import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class GetBooksListValidatorTest {
    @Test
    public void validateAdapterRequest(){
        List<GetBooksListAdapterRequest.SearchAttribute> searchAttributes = new ArrayList<>();
        searchAttributes.add(new GetBooksListAdapterRequest.SearchAttribute("title", "book title", GetBooksListAdapterRequest.Type.EQUAL));
        GetBooksListAdapterRequest request = new GetBooksListAdapterRequest(searchAttributes);
        assertDoesNotThrow(() -> GetBooksListValidator.validate(request));
    }
}
