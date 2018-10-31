package com.cyzest.texthandler.service;

import com.cyzest.texthandler.dto.TextGroupResult;
import com.cyzest.texthandler.dto.TextHandleParam;
import com.cyzest.texthandler.dto.TextType;
import com.cyzest.texthandler.exception.ParamException;
import com.cyzest.texthandler.handler.AlphabetOrNumTextConverter;
import com.cyzest.texthandler.handler.HtmlTagDeleteTextConverter;
import com.cyzest.texthandler.handler.TextConverter;
import com.cyzest.texthandler.handler.TextSorter;
import com.cyzest.texthandler.reader.UrlHtmlReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TextHandleServiceTest {

    @Mock
    private UrlHtmlReader urlHtmlReader;

    @Mock
    private Map<TextType, TextConverter> textTypeTextConvertMap;

    @Mock
    private TextSorter textSorter;

    @InjectMocks
    private TextHandleService textHandleService;

    private String mockUrl;
    private String mockHtml;

    @BeforeEach
    public void setUp() {
        mockUrl = "http://www.cyzest.com";
        mockHtml = "<html><body>TEST</body></html>";
    }

    @Test
    @DisplayName("createHandleResultTest() - TEXT 타입")
    public void createHandleResultTest1() throws Exception {

        TextType mockTextType = TextType.TEXT;
        String mockTextTypeConvertedText = "htmlbodyTESTbodyhtml";

        when(urlHtmlReader.read(mockUrl)).thenReturn(mockHtml);
        when(textTypeTextConvertMap.get(mockTextType)).thenReturn(new AlphabetOrNumTextConverter());
        when(textSorter.sort(mockTextTypeConvertedText)).thenReturn("bbddEhhllmmooSTTttyy");

        TextHandleParam textHandleParam = new TextHandleParam();
        textHandleParam.setUrl(mockUrl);
        textHandleParam.setTextType(mockTextType);
        textHandleParam.setTextGroupUnit(8);

        TextGroupResult textGroupResult = textHandleService.createHandleResult(textHandleParam);

        Assertions.assertNotNull(textGroupResult);
        Assertions.assertNotNull(textGroupResult.getGroupTextList());
        Assertions.assertEquals(2, textGroupResult.getGroupTextList().size());
        Assertions.assertEquals("bbddEhhl", textGroupResult.getGroupTextList().get(0));
        Assertions.assertEquals("lmmooSTT", textGroupResult.getGroupTextList().get(1));
        Assertions.assertNotNull(textGroupResult.getOtherText());
        Assertions.assertEquals("ttyy", textGroupResult.getOtherText());

        verify(urlHtmlReader, times(1)).read(mockUrl);
        verify(textTypeTextConvertMap, times(1)).get(mockTextType);
        verify(textSorter, times(1)).sort(mockTextTypeConvertedText);
    }

    @Test
    @DisplayName("createHandleResultTest() - HTML 타입")
    public void createHandleResultTest2() throws Exception {

        TextType mockTextType = TextType.TEXT;
        String mockTextTypeConvertedText = "TEST";

        when(urlHtmlReader.read(mockUrl)).thenReturn(mockHtml);
        when(textTypeTextConvertMap.get(mockTextType)).thenReturn(new HtmlTagDeleteTextConverter());
        when(textSorter.sort(mockTextTypeConvertedText)).thenReturn("ESTT");

        TextHandleParam textHandleParam = new TextHandleParam();
        textHandleParam.setUrl(mockUrl);
        textHandleParam.setTextType(mockTextType);
        textHandleParam.setTextGroupUnit(2);

        TextGroupResult textGroupResult = textHandleService.createHandleResult(textHandleParam);

        Assertions.assertNotNull(textGroupResult);
        Assertions.assertNotNull(textGroupResult.getGroupTextList());
        Assertions.assertEquals(2, textGroupResult.getGroupTextList().size());
        Assertions.assertEquals("ES", textGroupResult.getGroupTextList().get(0));
        Assertions.assertEquals("TT", textGroupResult.getGroupTextList().get(1));
        Assertions.assertNull(textGroupResult.getOtherText());

        verify(urlHtmlReader, times(1)).read(mockUrl);
        verify(textTypeTextConvertMap, times(1)).get(mockTextType);
        verify(textSorter, times(1)).sort(mockTextTypeConvertedText);
    }

    @Test
    @DisplayName("createHandleResultTest() - URL 예외 처리")
    public void createHandleResultTest3() throws Exception {

        TextType mockTextType = TextType.TEXT;

        when(urlHtmlReader.read(mockUrl)).thenReturn(null);

        TextHandleParam textHandleParam = new TextHandleParam();
        textHandleParam.setUrl(mockUrl);
        textHandleParam.setTextType(mockTextType);
        textHandleParam.setTextGroupUnit(2);

        Assertions.assertEquals(
                "html content null",
                Assertions.assertThrows(
                        ParamException.class,
                        () -> textHandleService.createHandleResult(textHandleParam)).getMessage());

        verify(urlHtmlReader, times(1)).read(mockUrl);
        verify(textTypeTextConvertMap, never()).get(mockTextType);
    }

    @Test
    @DisplayName("createHandleResultTest() -  Text Type 예외 처리")
    public void createHandleResultTest4() throws Exception {

        TextType mockTextType = TextType.TEXT;
        String mockTextTypeConvertedText = "TEST";

        when(urlHtmlReader.read(mockUrl)).thenReturn(mockHtml);
        when(textTypeTextConvertMap.get(mockTextType)).thenReturn(null);

        TextHandleParam textHandleParam = new TextHandleParam();
        textHandleParam.setUrl(mockUrl);
        textHandleParam.setTextType(mockTextType);
        textHandleParam.setTextGroupUnit(2);

        Assertions.assertEquals(
                "textType is not available",
                Assertions.assertThrows(
                        ParamException.class,
                        () -> textHandleService.createHandleResult(textHandleParam)).getMessage());

        verify(urlHtmlReader, times(1)).read(mockUrl);
        verify(textTypeTextConvertMap, times(1)).get(mockTextType);
        verify(textSorter, never()).sort(mockTextTypeConvertedText);
    }

}
