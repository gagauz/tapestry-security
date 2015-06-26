package org.gagauz.tapestry.security.api;


public class TextAccessAttribute implements AccessAttribute {
    private String text;

    public TextAccessAttribute(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
