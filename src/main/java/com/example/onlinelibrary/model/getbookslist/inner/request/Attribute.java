package com.example.onlinelibrary.model.getbookslist.inner.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attribute {
    private String attribute;
    private String value;
    private int type;

    public Attribute(String attribute, String value, int type) {
        this.attribute = attribute;
        this.value = value;
        this.type = type;
    }
}
