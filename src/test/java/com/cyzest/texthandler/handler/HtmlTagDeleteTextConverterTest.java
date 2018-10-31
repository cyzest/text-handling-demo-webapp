package com.cyzest.texthandler.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HtmlTagDeleteTextConverterTest {

    private HtmlTagDeleteTextConverter htmlTagDeleteTextConvert;

    @BeforeEach
    public void setUp() {
        this.htmlTagDeleteTextConvert = new HtmlTagDeleteTextConverter();
    }

    @Test
    public void convertTest1() {
        Assertions.assertNull(htmlTagDeleteTextConvert.convert(null));
    }

    @Test
    public void convertTest2() {
        Assertions.assertEquals(htmlTagDeleteTextConvert.convert(""), "");
    }

    @Test
    public void convertTest3() {
        String text = "<html ><head></head> <body>TEST<a href=\"http://cyzest.com?id=1#test\">LINK</a></body></ html>";
        Assertions.assertEquals(htmlTagDeleteTextConvert.convert(text), "TESTLINK");
    }

    @Test
    public void convertTest4() {
        String text = "<html ><head><title>TITLE</title></head> <body>TEST<한글></body></ html>";
        Assertions.assertEquals(htmlTagDeleteTextConvert.convert(text), "TITLETEST");
    }

}
