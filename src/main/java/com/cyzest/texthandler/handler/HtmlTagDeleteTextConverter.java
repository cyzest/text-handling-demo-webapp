package com.cyzest.texthandler.handler;

import org.jsoup.Jsoup;

/**
 * HTML 태그 부분을 삭제한 후 영문+숫자만 반환한다.
 */
public class HtmlTagDeleteTextConverter implements TextConverter {

    @Override
    public String convert(String text) {
        if (text != null && !text.isEmpty()) {
            text = Jsoup.parse(text).text();
            return text.replaceAll("[^A-Za-z0-9]", "");
        }
        return text;
    }
}
