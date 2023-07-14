package com.example.onlinelibrary.model.getbookslist;
import com.example.onlinelibrary.model.getbookslist.inner.request.SearchAttribute;

import java.util.ArrayList;
import java.util.List;

public class GetBooksListAdapterRequest {
    private List<SearchAttribute> searchAttributes;
    public GetBooksListAdapterRequest() {
        this.searchAttributes = new ArrayList<>();
    }

    public List<SearchAttribute> getSearchAttributes() {
        return searchAttributes;
    }
    public void setSearchAttributes(List<SearchAttribute> searchAttributes) {
        this.searchAttributes = searchAttributes;
    }

    public GetBooksListAdapterRequest(List<SearchAttribute> searchAttributes) {
        this.searchAttributes = searchAttributes;
    }


}
