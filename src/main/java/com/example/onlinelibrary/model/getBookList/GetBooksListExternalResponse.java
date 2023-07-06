package com.example.onlinelibrary.model.getBookList;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public class GetBooksListExternalResponse {
    private List<ExternalBook> externalBookData;

    public GetBooksListExternalResponse(List<ExternalBook> externalBookData) {
        this.externalBookData = externalBookData;
    }


    public List<ExternalBook> getExternalBookData() {
        return externalBookData;
    }

    public void setExternalBookData(List<ExternalBook> externalBookData) {
        this.externalBookData = externalBookData;
    }

    public static class ExternalBook {
        @NotBlank
        private Long id; //
        private String renter; //
        private LocalDateTime rentalStartTime; //
        private LocalDateTime rentalStopTime; //
        @NotBlank
        private String title; //
        @NotBlank
        private String publisher; //
        private String category; //

        private List<Long> recommendationIdList; //

        @NotBlank
        private List<ExternalContentData> externalContentData; //

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

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public ExternalBook(@NotBlank Long id, @NotBlank String title, String category, @NotBlank String publisher, String renter,
                            LocalDateTime rentalStartTime, LocalDateTime rentalStopTime, List<Long> recommendationList,
                            @NotBlank List<ExternalContentData> externalContentData) {
            this.id = id;
            this.title = title;
            this.category = category;
            this.publisher = publisher;
            this.renter = renter;
            this.rentalStartTime = rentalStartTime;
            this.rentalStopTime = rentalStopTime;
            this.recommendationIdList = recommendationList;
            this.externalContentData = externalContentData;
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

        public List<ExternalContentData> getExternalContentData() {
            return externalContentData;
        }

        public void setExternalContentData(List<ExternalContentData> externalContentData) {
            this.externalContentData = externalContentData;
        }

        public static class ExternalContentData {
            private String description;
            @NotBlank
            private String title;
            @NotBlank
            private String author;
            private String category;
            @NotBlank
            private List<Long> recommendationIdList;

            public ExternalContentData(@NotBlank String author, String description, @NotBlank String title,
                                       @NotBlank List<Long> recommendationIdList) {
                this.author = author;
                this.description = description;
                this.title = title;
                this.recommendationIdList = recommendationIdList;
            }

            public String getDescription() {
                return description;
            }

            public String getTitle() {
                return title;
            }

            public String getAuthor() {
                return author;
            }

            public String getCategory() {
                return category;
            }

            public List<Long> getRecommendationIdList() {
                return recommendationIdList;
            }
        }
    }
}
