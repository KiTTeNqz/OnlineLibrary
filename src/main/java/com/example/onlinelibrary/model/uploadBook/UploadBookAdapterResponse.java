package com.example.onlinelibrary.model.uploadBook;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class UploadBookAdapterResponse {

    private String status;
    private Long id;
    private ZonedDateTime dateOfUpload;

    public UploadBookAdapterResponse(String status, Long id, ZonedDateTime dateOfUpload) {
        this.status = status;
        this.id = id;
        this.dateOfUpload = dateOfUpload;
    }

    public UploadBookAdapterResponse(String status) {
        this.status = status;
    }

}
