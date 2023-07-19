package com.example.onlinelibrary.model.uploadBook;

import com.example.onlinelibrary.model.ContentData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UploadBookExternalRequest {
    private String title;

    private String publisher;

    private List<ContentData> contentData;

}
