package com.cyzest.texthandler.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.net.UnknownHostException;

public class JsoupUrlHtmlReaderTest {

    private JsoupUrlHtmlReader jsoupUrlHtmlReader;

    @BeforeEach
    public void setUp() {
        this.jsoupUrlHtmlReader = new JsoupUrlHtmlReader();
    }

    @Test
    public void readTest1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> jsoupUrlHtmlReader.read(null));
    }

    @Test
    public void readTest2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> jsoupUrlHtmlReader.read(""));
    }

    @Test
    public void readTest3() {
        Assertions.assertThrows(ConnectException.class, () -> jsoupUrlHtmlReader.read("http://cyzest.com"));
    }

    @Test
    public void readTest4() {
        Assertions.assertThrows(UnknownHostException.class, () -> jsoupUrlHtmlReader.read("http://cyzest2.com"));
    }

    @Test
    public void readTest5() throws Exception {
        Assertions.assertNotNull(jsoupUrlHtmlReader.read("http://www.naver.com"));
    }

}
