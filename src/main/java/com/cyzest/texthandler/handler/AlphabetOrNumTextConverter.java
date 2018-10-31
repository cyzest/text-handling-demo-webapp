package com.cyzest.texthandler.handler;

/**
 * 영문+숫자만 반환한다.
 */
public class AlphabetOrNumTextConverter implements TextConverter {

    @Override
    public String convert(String text) {
        if (text != null && !text.isEmpty()) {
            return text.replaceAll("[^A-Za-z0-9]", "");
        }
        return text;
    }
}
