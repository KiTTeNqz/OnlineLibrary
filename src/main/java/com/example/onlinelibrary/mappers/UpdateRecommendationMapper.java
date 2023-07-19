package com.example.onlinelibrary.mappers;

import com.example.onlinelibrary.model.updaterecommendation.UpdateRecommendationAdapterRequest;
import com.example.onlinelibrary.model.updaterecommendation.UpdateRecommendationExternalRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateRecommendationMapper {
    public UpdateRecommendationExternalRequest mapRequest(UpdateRecommendationAdapterRequest adapterRequest){
        return new UpdateRecommendationExternalRequest(
                String.valueOf(adapterRequest.getId()),
                adapterRequest.getRecommendationIdList().stream().map(String::valueOf).toList());
    }
}
