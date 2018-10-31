package com.cyzest.texthandler.reader;

public interface UrlHtmlReader {

    /**
     * URL 주로를 받아 HTML 페이지를 반환한다.
     *
     * @param url URL주소
     * @return HTML 문자열
     * @throws Exception
     */
    String read(String url) throws Exception;

}
