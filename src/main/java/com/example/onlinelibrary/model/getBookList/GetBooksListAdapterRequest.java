package com.example.onlinelibrary.model.getBookList;

import com.example.onlinelibrary.model.AttrConversion.Attribute;
import feign.ResponseMapper;

import java.util.List;

public class GetBooksListAdapterRequest {
    public List<SearchAttribute> getSearchAttributes() {
        return searchAttributes;
    }
    public void setSearchAttributes(List<SearchAttribute> searchAttributes) {
        this.searchAttributes = searchAttributes;
    }
    public GetBooksListAdapterRequest(List<SearchAttribute> searchAttributes) {
        this.searchAttributes = searchAttributes;
    }
    private List<SearchAttribute> searchAttributes;
    public static class SearchAttribute {
        String attribute;
        String value;
        Type type;

        public SearchAttribute(String attribute, String value, Type type) {
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

    }

    public enum Type{
        CONTAIN,
        EQUAL,
        NOT_EMPTY,
        BETWEEN
    }
}
