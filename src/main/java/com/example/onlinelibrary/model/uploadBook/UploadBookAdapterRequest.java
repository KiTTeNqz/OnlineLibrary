package com.example.onlinelibrary.model.uploadBook;
import com.example.onlinelibrary.model.uploadBook.inner.BookData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadBookAdapterRequest {
    private BookData bookData;

    @JsonCreator
    public UploadBookAdapterRequest(@JsonProperty("bookData") BookData bookData) {
        this.bookData = bookData;
    }

}