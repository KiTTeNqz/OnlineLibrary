package com.example.onlinelibrary.model.updaterecommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecommendationAdapterRequest {
    private Long id;
    private List<Long> recommendationIdList;
}
