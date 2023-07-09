package com.example.onlinelibrary.model.uploadBook;

import com.example.onlinelibrary.model.ContentData;

import java.util.List;

public class UploadBookExternalRequest {
    private String title;

    private String publisher;

    private List<ContentData> contentData;

    public UploadBookExternalRequest(String title, String publisher, List<ContentData> contentData) {
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
