package com.example.onlinelibrary.model.getbookslist;
import com.example.onlinelibrary.model.getbookslist.inner.response.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetBooksListAdapterResponse {
    private Integer count;

    private List<Book> bookData;

    public GetBooksListAdapterResponse() {
        this.bookData = new ArrayList<>();
        this.count = 0;
    }
}
