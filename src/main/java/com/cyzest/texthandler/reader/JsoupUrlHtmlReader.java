package com.cyzest.texthandler.reader;

import org.jsoup.Jsoup;

/**
 * Jsoup Based URL HTML Reader
 */
public class JsoupUrlHtmlReader implements UrlHtmlReader {

    @Override
    public String read(String url) throws Exception {
        return Jsoup.connect(url).get().html();
    }
}
