package com.example.onlinelibrary.exceptions;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;

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
    }

    public ExceptionResponse(String error, String status) {
        this.errorCode = error;
        this.messages = "";
        this.timestamp = Instant.now().toEpochMilli();
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
