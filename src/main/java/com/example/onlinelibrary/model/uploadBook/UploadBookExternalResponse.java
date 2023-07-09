package com.example.onlinelibrary.model.uploadBook;

import java.time.LocalDateTime;

public class UploadBookExternalResponse {
    private Integer status_code;
    private Long id;
    private LocalDateTime date_of_upload;
    private String err;

    public UploadBookExternalResponse(Integer status_code, Long id, LocalDateTime date_of_upload, String err) {
        this.status_code = status_code;
        this.id = id;
        this.date_of_upload = date_of_upload;
        this.err = err;
    }

    public UploadBookExternalResponse(Integer status_code, Long id, LocalDateTime date_of_upload) {
        this.status_code = status_code;
        this.id = id;
        this.date_of_upload = date_of_upload;
        err = "";
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate_of_upload() {
        return date_of_upload;
    }

    public void setDate_of_upload(LocalDateTime date_of_upload) {
        this.date_of_upload = date_of_upload;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
