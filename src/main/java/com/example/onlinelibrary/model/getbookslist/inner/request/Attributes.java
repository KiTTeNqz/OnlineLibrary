package com.example.onlinelibrary.model.getbookslist.inner.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Attributes {
    private List<Attribute> attributes;

    public Attributes(List<Attribute> attributeList) {
        this.attributes = attributeList;
    }

    public Attributes() {
        this.attributes = new ArrayList<>();
    }

}
