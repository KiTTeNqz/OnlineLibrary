package com.example.onlinelibrary.model.uploadBook;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UploadBookExternalResponse {
    private Integer status_code;
    private Long id;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime date_of_upload;
    private String err;

    public UploadBookExternalResponse() {
    }

    public UploadBookExternalResponse(Integer status_code, String err) {
        this.status_code = status_code;
        this.err = err;
    }

    public UploadBookExternalResponse(Integer status_code) {
        this.status_code = status_code;
        this.err = "Unknown Error";
    }

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
}
