package com.example.onlinelibrary.validator;

import com.example.onlinelibrary.exceptions.ExceptionResponse;

public class HeaderValidator {
    public static void validateXTraceId(String traceId) throws ExceptionResponse {
        if (traceId == null || traceId.isEmpty()) {
            throw new ExceptionResponse("ERR-002", "error", "Ошибка при валидации запроса");
        }
    }
}