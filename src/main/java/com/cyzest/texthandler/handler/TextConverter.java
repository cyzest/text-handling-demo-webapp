package com.cyzest.texthandler.handler;

public interface TextConverter {

    /**
     * 문자열을 입력받아 변환한다.
     *
     * @param text 변환할 문자열
     * @return 변환된 문자열
     */
    String convert(String text);

}
