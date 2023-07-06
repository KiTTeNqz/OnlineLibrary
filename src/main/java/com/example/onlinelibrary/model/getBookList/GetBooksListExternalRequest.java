package com.example.onlinelibrary.model.getBookList;

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
    public static class Attributes{
        private List<Attribute> attributes;

        public Attributes() {
            this.attributes = new ArrayList<>();
        }

        public Attributes(List<Attribute> attributes) {
            this.attributes = attributes;
        }
        public List<Attribute> getAttributes() {
            return attributes;
        }
        public void setAttributes(List<Attribute> attributes) {
            this.attributes = attributes;
        }
    }
    public static class Attribute {
        private String attribute;
        private String value;
        private int type;
        public Attribute(String attribute, String value, int type) {
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
        public int getType() {
            return type;
        }
        public void setType(int type) {
            this.type = type;
        }
    }
}
