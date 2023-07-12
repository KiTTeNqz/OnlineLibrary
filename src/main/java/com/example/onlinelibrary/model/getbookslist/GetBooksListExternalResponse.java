package com.example.onlinelibrary.model.getbookslist;

import com.example.onlinelibrary.model.ContentData;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class GetBooksListExternalResponse {
    private List<ExternalBook> bookData;

    public GetBooksListExternalResponse(List<ExternalBook> bookData) {
        this.bookData = bookData;
    }

    public GetBooksListExternalResponse() {
    }

    public List<ExternalBook> getBookData() {
        return bookData;
    }

    public void setBookData(List<ExternalBook> bookData) {
        this.bookData = bookData;
    }

    public static class ExternalBook {
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

        public LocalDateTime getRentalStartTime() {
            return rentalStartTime;
        }

        public void setRentalStartTime(LocalDateTime rentalStartTime) {
            this.rentalStartTime = rentalStartTime;
        }

        public LocalDateTime getRentalStopTime() {
            return rentalStopTime;
        }

        public void setRentalStopTime(LocalDateTime rentalStopTime) {
            this.rentalStopTime = rentalStopTime;
        }

        public ExternalBook(Long id, String renter, LocalDateTime rentalStartTime, LocalDateTime rentalStopTime,
                            String title, String publisher, List<Long> recommendationIdList,
                            List<ContentData> contentData) {
            this.id = id;
            this.renter = renter;
            this.rentalStartTime = rentalStartTime;
            this.rentalStopTime = rentalStopTime;
            this.title = title;
            this.publisher = publisher;
            this.recommendationIdList = recommendationIdList;
            this.contentData = contentData;
        }

        public ExternalBook() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getRenter() {
            return renter;
        }

        public void setRenter(String renter) {
            this.renter = renter;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public List<Long> getRecommendationIdList() {
            return recommendationIdList;
        }

        public void setRecommendationIdList(List<Long> recommendationIdList) {
            this.recommendationIdList = recommendationIdList;
        }

        public List<ContentData> getContentData() {
            return contentData;
        }

        public void setContentData(List<ContentData> contentData) {
            this.contentData = contentData;
        }

    }
}
