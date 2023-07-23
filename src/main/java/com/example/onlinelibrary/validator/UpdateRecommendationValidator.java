package com.example.onlinelibrary.validator;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.updaterecommendation.UpdateRecommendationAdapterRequest;

public class UpdateRecommendationValidator {
    public static void validate(UpdateRecommendationAdapterRequest request) throws ExceptionResponse {

        if (request == null) {
            throw new ExceptionResponse("ERR-002","error","Ошибка при валидации запроса");
        }

        if(request.getId()==null
                || request.getRecommendationIdList()==null
                || request.getRecommendationIdList().isEmpty())
            throw new ExceptionResponse("ERR-002","error","Ошибка при валидации запроса");

    }
}
