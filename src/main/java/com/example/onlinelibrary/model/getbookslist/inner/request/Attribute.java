package com.example.onlinelibrary.model.getbookslist.inner.request;

public class Attribute {
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
