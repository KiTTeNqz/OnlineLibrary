package com.example.onlinelibrary.validator;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.getbookslist.GetBooksListAdapterRequest;
import com.example.onlinelibrary.model.getbookslist.inner.request.SearchAttribute;

public class GetBooksListValidator {
    public static void validate(GetBooksListAdapterRequest request) throws ExceptionResponse {

        if (request == null
                || request.getSearchAttributes() == null
                || request.getSearchAttributes().isEmpty()) {
            throw new ExceptionResponse("ERR-002","error","Ошибка при валидации запроса");
        }

        for(SearchAttribute searchAttribute: request.getSearchAttributes()){
            if(searchAttribute.getAttribute().isBlank()||
            searchAttribute.getValue()==null)
                throw new ExceptionResponse("ERR-002","error","Ошибка при валидации запроса");
        }
    }
}
