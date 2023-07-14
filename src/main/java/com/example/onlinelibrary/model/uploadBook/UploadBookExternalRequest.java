package com.example.onlinelibrary.model.uploadBook;

import com.example.onlinelibrary.model.ContentData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UploadBookExternalRequest {
    private String title;

    private String publisher;

    private List<ContentData> contentData;

    public UploadBookExternalRequest(String title, String publisher, List<ContentData> contentData) {
        this.title = title;
        this.publisher = publisher;
        this.contentData = contentData;
    }
}
