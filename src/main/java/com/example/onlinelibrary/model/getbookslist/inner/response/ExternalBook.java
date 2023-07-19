package com.example.onlinelibrary.model.getbookslist.inner.response;

import com.example.onlinelibrary.model.ContentData;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExternalBook {
    @NotNull
    private Long id;
    private String renter;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime rentalStartTime;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime rentalStopTime;
    @NotBlank
    private String title;
    @NotBlank
    private String publisher;
    private List<Long> recommendationIdList;
    @NotBlank
    private List<ContentData> contentData;
}
