package com.example.onlinelibrary.model.uploadBook;

import java.time.ZonedDateTime;

public class UploadBookAdapterResponse {

    private String status;
    private Long id;
    private ZonedDateTime dateOfUpload;

    public UploadBookAdapterResponse(String status, Long id, ZonedDateTime dateOfUpload) {
        this.status = status;
        this.id = id;
        this.dateOfUpload = dateOfUpload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateOfUpload() {
        return dateOfUpload;
    }

    public void setDateOfUpload(ZonedDateTime dateOfUpload) {
        this.dateOfUpload = dateOfUpload;
    }
}
