package com.example.onlinelibrary.model.getbookslist;

import com.example.onlinelibrary.model.getbookslist.inner.request.Attributes;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetBooksListExternalRequest {

    public List<Attributes> bookData;

    public GetBooksListExternalRequest() {
        this.bookData = new ArrayList<>();
    }

    public GetBooksListExternalRequest(List<Attributes> bookData) {
        this.bookData = bookData;
    }

}
