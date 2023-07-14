package com.example.onlinelibrary.model.getbookslist.inner.request;
import java.util.ArrayList;
import java.util.List;

public class Attributes {
        private List<Attribute> attributes;

        public Attributes(List<Attribute> attributeList) {
            this.attributes = new ArrayList<>();
        }

        public List<Attribute> getAttributes() {
            return attributes;
        }
        public void setAttributes(List<Attribute> attributes) {
            this.attributes = attributes;
        }
}
