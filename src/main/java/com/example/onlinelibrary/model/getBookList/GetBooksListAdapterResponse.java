package com.example.onlinelibrary.model.getBookList;

import com.example.onlinelibrary.model.ContentData;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class GetBooksListAdapterResponse {
    private Integer count;

    private List<Book> bookData;

    public GetBooksListAdapterResponse() {
        this.bookData = new ArrayList<>();
        this.count = 0;
    }

    public Integer getCount() {
        return count;
    }

    public GetBooksListAdapterResponse(Integer count, List<Book> bookData) {
        this.count = count;
        this.bookData = bookData;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setBookData(List<Book> bookData) {
        this.bookData = bookData;
    }

    public List<Book> getBookData() {
        return bookData;
    }

    public static class Book {
        @NotBlank
        private Long id; //
        private String renter; //
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
        private ZonedDateTime rentalStartTime; //
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
        private ZonedDateTime rentalStopTime; //

        public Long getId() {
            return id;
        }

        public String getRenter() {
            return renter;
        }

        public String getTitle() {
            return title;
        }

        public String getPublisher() {
            return publisher;
        }

        public List<Long> getRecommendationIdList() {
            return recommendationIdList;
        }

        public List<ContentData> getExternalContentData() {
            return externalContentData;
        }

        @NotBlank
        private String title; //
        @NotBlank
        private String publisher; //

        private List<Long> recommendationIdList; //

        @NotBlank
        private List<ContentData> externalContentData; //

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

        public Book(@NotBlank Long id, @NotBlank String title, @NotBlank String publisher, String renter,
                    ZonedDateTime rentalStartTime, ZonedDateTime rentalStopTime, List<Long> recommendationList,
                    @NotBlank List<ContentData> externalContentData) {

            this.id = id;
            this.title = title;
            this.publisher = publisher;
            this.renter = renter;
            this.rentalStartTime = rentalStartTime;
            this.rentalStopTime = rentalStopTime;
            this.recommendationIdList = recommendationList;
            this.externalContentData = externalContentData;
        }
    }
}
