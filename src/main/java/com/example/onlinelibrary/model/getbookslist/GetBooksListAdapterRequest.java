package com.example.onlinelibrary.model.getbookslist;
import com.example.onlinelibrary.model.getbookslist.inner.request.SearchAttribute;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetBooksListAdapterRequest {

    private List<SearchAttribute> searchAttributes;
    public GetBooksListAdapterRequest() {
        this.searchAttributes = new ArrayList<>();
    }

    public List<SearchAttribute> getSearchAttributes() {
        return searchAttributes;
    }

    public GetBooksListAdapterRequest(List<SearchAttribute> searchAttributes) {
        this.searchAttributes = searchAttributes;
    }


}
