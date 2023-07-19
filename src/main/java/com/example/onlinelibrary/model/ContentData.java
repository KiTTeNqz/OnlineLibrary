package com.example.onlinelibrary.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContentData {
    private String description;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    private String category;
    @NotBlank
    private List<Long> recommendationIdList;

    public ContentData(String description, @NotBlank String title, @NotBlank String author,
                       String category, @NotBlank List<Long> recommendationIdList) {
        this.author = author;
        this.description = description;
        this.title = title;
        this.category = category;
        this.recommendationIdList = recommendationIdList;
    }

    public ContentData() {
    }
}
