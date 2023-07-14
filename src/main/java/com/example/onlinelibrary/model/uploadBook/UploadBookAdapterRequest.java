package com.example.onlinelibrary.model.uploadBook;
import com.example.onlinelibrary.model.uploadBook.inner.BookData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadBookAdapterRequest {
    private BookData bookData;

    @JsonCreator
    public UploadBookAdapterRequest(@JsonProperty("bookData") BookData bookData) {
        this.bookData = bookData;
    }

    public BookData getBookData() {
        return bookData;
    }

    public void setBookData(BookData bookData) {
        this.bookData = bookData;
    }
}