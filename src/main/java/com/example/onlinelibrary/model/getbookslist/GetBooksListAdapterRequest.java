package com.example.onlinelibrary.model.getbookslist;
import com.example.onlinelibrary.model.getbookslist.inner.request.SearchAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetBooksListAdapterRequest {

    private List<SearchAttribute> searchAttributes;
    public GetBooksListAdapterRequest() {
        this.searchAttributes = new ArrayList<>();
    }
    public List<SearchAttribute> getSearchAttributes() {
        return searchAttributes;
    }
}
