package com.example.onlinelibrary.validator;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.getBookList.GetBooksListAdapterRequest;

public class GetBookListValidator {
    public static boolean validate(GetBooksListAdapterRequest request) throws ExceptionResponse {
        for(GetBooksListAdapterRequest.SearchAttribute searchAttribute: request.getSearchAttributes()){
            if(searchAttribute.getAttribute().isBlank()||
            searchAttribute.getValue().isBlank())
                throw new ExceptionResponse("ERR-002","error","Ошибка при валидации запроса");
        }
        return true;
    }
}
