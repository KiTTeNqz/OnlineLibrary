package com.example.onlinelibrary.validator;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.ContentData;

public class ContentDataValidator {
    public static void validate(ContentData contentData) throws ExceptionResponse {

        if(contentData==null)
            throw new ExceptionResponse("ERR-002","error","Ошибка при валидации запроса");

        if(contentData.getAuthor().isBlank()||
            contentData.getTitle().isBlank())
            throw new ExceptionResponse("ERR-002","error","Ошибка при валидации запроса");
    }
}
