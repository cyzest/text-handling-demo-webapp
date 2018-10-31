package com.cyzest.texthandler.reader;

import org.jsoup.Jsoup;

/**
 * JSOP Based URL HTML Reader
 */
public class JsopUrlHtmlReader implements UrlHtmlReader {

    @Override
    public String read(String url) throws Exception {
        return Jsoup.connect(url).get().html();
    }
}
