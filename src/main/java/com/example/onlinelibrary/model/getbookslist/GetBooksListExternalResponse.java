package com.example.onlinelibrary.model.getbookslist;

import com.example.onlinelibrary.model.ContentData;
import com.example.onlinelibrary.model.getbookslist.inner.response.ExternalBook;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class GetBooksListExternalResponse {
    private List<ExternalBook> bookData;

    public GetBooksListExternalResponse(List<ExternalBook> bookData) {
        this.bookData = bookData;
    }

    public GetBooksListExternalResponse() {
    }

    public List<ExternalBook> getBookData() {
        return bookData;
    }

    public void setBookData(List<ExternalBook> bookData) {
        this.bookData = bookData;
    }


}
