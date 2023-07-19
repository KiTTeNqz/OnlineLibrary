package com.example.onlinelibrary.model.getbookslist.inner.response;

import com.example.onlinelibrary.model.ContentData;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Book {

    @NotBlank
    private Long id; //
    @NotBlank
    private String title; //
    @NotBlank
    private String publisher; //
    private String renter; //
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime rentalStartTime; //
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private ZonedDateTime rentalStopTime; //
    private List<Long> recommendationIdList; //
    @NotBlank
    private List<ContentData> externalContentData; //
}
