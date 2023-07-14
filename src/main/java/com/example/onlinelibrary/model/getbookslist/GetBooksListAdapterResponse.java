package com.example.onlinelibrary.model.getbookslist;
import com.example.onlinelibrary.model.getbookslist.inner.response.Book;
import java.util.ArrayList;
import java.util.List;

public class GetBooksListAdapterResponse {
    private Integer count;

    private List<Book> bookData;

    public GetBooksListAdapterResponse() {
        this.bookData = new ArrayList<>();
        this.count = 0;
    }

    public Integer getCount() {
        return count;
    }

    public GetBooksListAdapterResponse(Integer count, List<Book> bookData) {
        this.count = count;
        this.bookData = bookData;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setBookData(List<Book> bookData) {
        this.bookData = bookData;
    }

    public List<Book> getBookData() {
        return bookData;
    }


}
