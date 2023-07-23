package com.example.onlinelibrary.validator;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterRequest;

public class UploadBookValidator {
    public static void validate(UploadBookAdapterRequest request) throws ExceptionResponse {

        if (request == null
                || request.getBookData() == null
                || request.getBookData().getPublisher()==null
                || request.getBookData().getTitle()==null) {
            throw new ExceptionResponse("ERR-002","error","Ошибка при валидации запроса");
        }

        if(request.getBookData().getTitle().isBlank()
                || request.getBookData().getPublisher().isBlank()
                || request.getBookData().getContentData().isEmpty())
            throw new ExceptionResponse("ERR-002","error","Ошибка при валидации запроса");
    }
}
