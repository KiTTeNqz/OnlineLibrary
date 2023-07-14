package com.example.onlinelibrary.validator;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.ContentData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ContentDataValidatorTest {
    @Test
    public void validateContentData() throws ExceptionResponse {
        List<Long> recommendationIdList = new ArrayList<>();
        recommendationIdList.add(4L);
        recommendationIdList.add(5L);
        recommendationIdList.add(6L);

        ContentData contentData = new ContentData("Description", "Content Title", "Author", "poem", recommendationIdList);
        assertDoesNotThrow(() -> ContentDataValidator.validate(contentData));
    }
}
