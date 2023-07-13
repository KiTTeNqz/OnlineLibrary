package com.example.onlinelibrary.model.uploadBook.inner;

import com.example.onlinelibrary.model.ContentData;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<ContentData> getContentData() {
        return contentData;
    }

    public void setContentData(List<ContentData> contentData) {
        this.contentData = contentData;
    }
}
