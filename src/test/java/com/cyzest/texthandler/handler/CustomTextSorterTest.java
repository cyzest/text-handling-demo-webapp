package com.cyzest.texthandler.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomTextSorterTest {

    private CustomTextSorter customTextSort;

    @BeforeEach
    public void setUp() {
        this.customTextSort = new CustomTextSorter();
    }

    @Test
    public void sortTest1() {
        Assertions.assertNull(customTextSort.sort(null));
    }

    @Test
    public void sortTest2() {
        Assertions.assertEquals("", customTextSort.sort(""));
    }

    @Test
    public void sortTest3() {
        Assertions.assertEquals("A0a7b8c9Z!#", customTextSort.sort("#!0abA798Zc"));
    }

    @Test
    public void sortTest4() {
        Assertions.assertEquals("A0A0a7a7b8b8c9c9ZZ!!##", customTextSort.sort("#!0abA798Zc0abA798Zc!#"));
    }

    @Test
    public void sortTest5() {
        Assertions.assertEquals("bbddEhhllmmooSTTttyy", customTextSort.sort("htmlbodyTESTbodyhtml"));
    }

}
