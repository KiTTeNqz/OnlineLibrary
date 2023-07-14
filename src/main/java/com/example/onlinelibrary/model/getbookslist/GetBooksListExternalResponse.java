package com.example.onlinelibrary.model.getbookslist;

import com.example.onlinelibrary.model.getbookslist.inner.response.ExternalBook;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GetBooksListExternalResponse {
    private List<ExternalBook> bookData;

    public GetBooksListExternalResponse(List<ExternalBook> bookData) {
        this.bookData = bookData;
    }

    public GetBooksListExternalResponse() {
    }


}
