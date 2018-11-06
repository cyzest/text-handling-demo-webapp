package com.cyzest.texthandler.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlphabetOrNumTextConverterTest {

    private AlphabetOrNumTextConverter alphabetOrNumTextConverter;

    @BeforeEach
    public void setUp() {
        this.alphabetOrNumTextConverter = new AlphabetOrNumTextConverter();
    }

    @Test
    public void convertTest1() {
        Assertions.assertNull(alphabetOrNumTextConverter.convert(null));
    }

    @Test
    public void convertTest2() {
        Assertions.assertEquals("", alphabetOrNumTextConverter.convert(""));
    }

    @Test
    public void convertTest3() {
        String text = "<html ><head></head> <body>TEST<a href=\"http://cyzest.com?id=1#test\">LINK</a></body></ html>";
        String convertText = "htmlheadheadbodyTESTahrefhttpcyzestcomid1testLINKabodyhtml";
        Assertions.assertEquals(convertText, alphabetOrNumTextConverter.convert(text));
    }

}
