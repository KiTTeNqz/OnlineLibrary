package com.example.onlinelibrary.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;


public class Book {
    @NotBlank
    private Long id; //
    @NotBlank
    private String title; //
    private String category; //
    @NotBlank
    private String publisher; //
    private String renter; //
    private ZonedDateTime rentalStartTime; //
    private ZonedDateTime rentalStopTime; //
    private List<Long> recommendationList; //

    public ZonedDateTime getRentalStartTime() {
        return rentalStartTime;
    }

    public void setRentalStartTime(ZonedDateTime rentalStartTime) {
        this.rentalStartTime = rentalStartTime;
    }

    public ZonedDateTime getRentalStopTime() {
        return rentalStopTime;
    }

    public void setRentalStopTime(ZonedDateTime rentalStopTime) {
        this.rentalStopTime = rentalStopTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Book(@NotBlank Long id, @NotBlank String title, String category, @NotBlank String publisher, String renter,
                LocalDateTime rentalStartTime, LocalDateTime rentalStopTime, List<Long> recommendationList,
                @NotBlank List<Content> contentData) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.publisher = publisher;
        this.renter = renter;
        this.rentalStartTime = ZonedDateTime.of(rentalStartTime, ZoneOffset.ofHours(5));
        this.rentalStopTime = ZonedDateTime.of(rentalStopTime, ZoneOffset.ofHours(5));
        this.recommendationList = recommendationList;
        this.contentData = contentData;
    }

    @NotBlank
    private List<Content> contentData; //


    static class Content {

        @NotBlank
        private String author;
        private String description;
        @NotBlank
        private String title;
        @NotBlank
        private List<Long> recommendationIdList;

        public Content(@NotBlank String author, String description, @NotBlank String title,
                       @NotBlank List<Long> recommendationIdList) {
            this.author = author;
            this.description = description;
            this.title = title;
            this.recommendationIdList = recommendationIdList;
        }

    }

}
