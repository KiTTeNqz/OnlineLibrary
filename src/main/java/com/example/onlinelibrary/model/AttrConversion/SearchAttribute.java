package com.example.onlinelibrary.model.AttrConversion;

public class SearchAttribute {
    String attribute;
    String value;
    Type type;

    public SearchAttribute(String attribute, String value, Type type) {
        this.attribute = attribute;
        this.value = value;
        this.type = type;
    }

    public Attribute convertToAttribute(){
        return new Attribute(this.attribute, this.value, this.type.ordinal()+1);
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

enum Type{
    CONTAIN,
    EQUAL,
    NOT_EMPTY,
    BETWEEN
}
