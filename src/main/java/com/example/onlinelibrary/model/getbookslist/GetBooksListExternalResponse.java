package com.example.onlinelibrary.model.getbookslist;

import com.example.onlinelibrary.model.getbookslist.inner.response.ExternalBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetBooksListExternalResponse {
    private List<ExternalBook> bookData;
}
