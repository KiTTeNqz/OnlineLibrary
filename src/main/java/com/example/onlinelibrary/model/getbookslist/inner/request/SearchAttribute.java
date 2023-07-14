package com.example.onlinelibrary.model.getbookslist.inner.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchAttribute {
    String attribute;
    String value;
    Type type;

    @JsonCreator
    public SearchAttribute(
            @JsonProperty("searchAttribute") String attribute,
            @JsonProperty("value") String value,
            @JsonProperty("searchType") Type type) {
        this.attribute = attribute;
        this.value = value;
        this.type = type;
    }


    public String getAttribute() {
        return attribute;
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum Type{
        CONTAIN,
        EQUAL,
        NOT_EMPTY,
        BETWEEN;

    }


}

