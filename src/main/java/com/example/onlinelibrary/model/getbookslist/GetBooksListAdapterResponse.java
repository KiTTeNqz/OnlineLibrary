package com.example.onlinelibrary.model.getbookslist;
import com.example.onlinelibrary.model.getbookslist.inner.response.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetBooksListAdapterResponse {
    private Integer count;

    private List<Book> bookData;

    public GetBooksListAdapterResponse() {
        this.bookData = new ArrayList<>();
        this.count = 0;
    }

    public GetBooksListAdapterResponse(Integer count, List<Book> bookData) {
        this.count = count;
        this.bookData = bookData;
    }

}
