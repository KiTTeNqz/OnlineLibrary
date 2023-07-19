package com.example.onlinelibrary.model.uploadBook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UploadBookAdapterResponse {

    private String status;
    private Long id;
    private ZonedDateTime dateOfUpload;

    public UploadBookAdapterResponse(String status) {
        this.status = status;
    }

}
