package com.example.onlinelibrary.exceptions;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ExceptionResponse extends Exception{
    @NotBlank
    private String errorCode;

    private String messages;
    @NotBlank
    private Long timestamp;
    @NotBlank
    private String status;

    public ExceptionResponse(String error, String status, String message) {
        super(message);
        this.errorCode = error;
        this.timestamp = Instant.now().toEpochMilli();
        this.status = status;
        this.messages = message;
    }

    public ExceptionResponse(String error, String status) {
        this.errorCode = error;
        this.messages = "";
        this.timestamp = Instant.now().toEpochMilli();
        this.status = status;
    }
}
