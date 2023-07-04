package com.example.onlinelibrary.exceptions;

import java.time.Instant;

public class ErrorResponse {
    private String errorCode;
    private String messages;
    private Long timestamp;

    private String status;

    public ErrorResponse(String error, String status, String message) {
        this.errorCode = error;
        this.messages = message;
        this.timestamp = Instant.now().toEpochMilli();
        this.status = status;
    }

    public ErrorResponse(String error, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}