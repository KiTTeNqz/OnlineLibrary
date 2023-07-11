package com.example.onlinelibrary.validator;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.getBookList.GetBooksListAdapterRequest;

public class GetBooksListValidator {
    public static void validate(GetBooksListAdapterRequest request) throws ExceptionResponse {
        for(GetBooksListAdapterRequest.SearchAttribute searchAttribute: request.getSearchAttributes()){
            if(searchAttribute.getAttribute().isBlank()||
            searchAttribute.getValue()==null)
                throw new ExceptionResponse("ERR-002","error","Ошибка при валидации запроса");
        }
    }
}
