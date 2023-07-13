package com.example.onlinelibrary.mappers;

import com.example.onlinelibrary.exceptions.ExceptionResponse;
import com.example.onlinelibrary.model.ContentData;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookAdapterResponse;
import com.example.onlinelibrary.model.uploadBook.UploadBookExternalRequest;
import com.example.onlinelibrary.model.uploadBook.UploadBookExternalResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class UploadBookMapper {
    public UploadBookExternalRequest mapRequest(UploadBookAdapterRequest request) {
        UploadBookExternalRequest externalRequest = new UploadBookExternalRequest(
                request.getBookData().getTitle(),
                request.getBookData().getPublisher(),
                request.getBookData().getContentData().stream().map(this::mapContentData).toList()
        );
        return externalRequest;
    }

    public UploadBookAdapterResponse mapResponse(UploadBookExternalResponse response) throws ExceptionResponse {
        if(response.getStatus_code()==0)
            return mapFullResponse(response);
        else return mapPartlyResponse(response);
    }

    private UploadBookAdapterResponse mapFullResponse(UploadBookExternalResponse response) throws ExceptionResponse{
        return new UploadBookAdapterResponse(
                mapStatusCode(response.getStatus_code()),
                response.getId(),
                mapDateOfUpload(response.getDate_of_upload())
        );
    }
    private UploadBookAdapterResponse mapPartlyResponse(UploadBookExternalResponse response) throws ExceptionResponse{
        return new UploadBookAdapterResponse(
                mapStatusCode(response.getStatus_code())
        );
    }
    private String mapStatusCode(Integer statusCode) throws ExceptionResponse {
        switch(statusCode){
            case 0:
                return "SUCCESS";
            case -1:
                throw new ExceptionResponse("ERR-003", "error", "Ошибка обработке создания/изменения внешнего объекта.");
            case -2:
                throw new ExceptionResponse("ERR-005", "error", "Ошибка при подготовке данных во внешней системе. Возникает, если данные для передачи не подготовлены.");
            default:
                throw new ExceptionResponse("ERR-004", "error", "Бизнес-ошибка, произошедшая внутри системы-получателя при выполнении операции");

        }
    }

    private ZonedDateTime mapDateOfUpload(LocalDateTime dateOfUpload) {
        return ZonedDateTime.of(
                        dateOfUpload,
                        ZoneOffset.ofHours(5))
                .withZoneSameInstant(ZoneId.of("+03:00"));
    }

    private ContentData mapContentData(ContentData externalContentData){
        return new ContentData(
                externalContentData.getDescription(),
                externalContentData.getTitle(),
                externalContentData.getAuthor(),
                mapCategory(externalContentData.getCategory()),
                externalContentData.getRecommendationIdList()
        );
    }

    private String mapCategory(String category) {
        Map<String, String> categoryMapping = new HashMap<>();
        categoryMapping.put("novel", "Роман");
        categoryMapping.put("story", "Рассказ");
        categoryMapping.put("poem", "Стихотворение");

        if (categoryMapping.containsKey(category)) {
            return categoryMapping.get(category);
        }
        return category;
    }
}
