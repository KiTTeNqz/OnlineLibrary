package com.example.onlinelibrary.model.uploadBook.inner;

import com.example.onlinelibrary.model.ContentData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookData {
    private String title;
    private String publisher;
    private List<ContentData> contentData;

    @JsonCreator
    public BookData(
            @JsonProperty("title") String title,
            @JsonProperty("publisher") String publisher,
            @JsonProperty("contentData") List<ContentData> contentData) {
        this.title = title;
        this.publisher = publisher;
        this.contentData = contentData;
    }
}
