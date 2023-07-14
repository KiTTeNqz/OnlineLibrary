package com.example.onlinelibrary.model.getbookslist;

import com.example.onlinelibrary.model.getbookslist.inner.request.Attributes;

import java.util.ArrayList;
import java.util.List;

public class GetBooksListExternalRequest {

    public List<Attributes> bookData;

    public GetBooksListExternalRequest() {
        this.bookData = new ArrayList<>();
    }

    public GetBooksListExternalRequest(List<Attributes> bookData) {
        this.bookData = bookData;
    }
    public List<Attributes> getBookData() {
        return bookData;
    }
    public void setBookData(List<Attributes> bookData) {
        this.bookData = bookData;
    }

}
