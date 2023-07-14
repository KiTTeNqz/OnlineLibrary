package com.example.onlinelibrary.validator;

import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getbookslist.inner.request.SearchAttribute;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class GetBooksListValidatorTest {
    @Test
    public void validateAdapterRequest(){
        List<SearchAttribute> searchAttributes = new ArrayList<>();
        searchAttributes.add(new SearchAttribute("title", "book title", SearchAttribute.Type.EQUAL));
        GetBooksListAdapterRequest request = new GetBooksListAdapterRequest(searchAttributes);
        assertDoesNotThrow(() -> GetBooksListValidator.validate(request));
    }
}
