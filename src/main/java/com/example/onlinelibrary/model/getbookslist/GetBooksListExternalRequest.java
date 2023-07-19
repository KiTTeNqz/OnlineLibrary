package com.example.onlinelibrary.model.getbookslist;

import com.example.onlinelibrary.model.getbookslist.inner.request.Attributes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetBooksListExternalRequest {

    private List<Attributes> bookData;

    public GetBooksListExternalRequest() {
        this.bookData = new ArrayList<>();
    }
}
