package com.cyzest.texthandler.controller;

import com.cyzest.texthandler.dto.TextGroupResult;
import com.cyzest.texthandler.dto.TextHandleParam;
import com.cyzest.texthandler.dto.TextType;
import com.cyzest.texthandler.exception.ParamException;
import com.cyzest.texthandler.service.TextHandleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TextHandleControllerTest {

    @Mock
    private TextHandleService textHandleService;

    @InjectMocks
    private TextHandleController textHandleController;

    private String mockUrl;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mockUrl = "http://www.cyzest.com";
        mvc = MockMvcBuilders.standaloneSetup(textHandleController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("getHandleTextTest() - Basic")
    public void getHandleTextTest1() throws Exception {

        TextHandleParam textHandleParam = new TextHandleParam();
        textHandleParam.setUrl(mockUrl);
        textHandleParam.setTextType(TextType.TEXT);
        textHandleParam.setTextGroupUnit(8);

        TextGroupResult textGroupResult = new TextGroupResult();
        textGroupResult.setGroupTextList(Arrays.asList("bbddEhhl", "lmmooSTT"));
        textGroupResult.setOtherText("ttyy");

        when(textHandleService.createHandleResult(textHandleParam)).thenReturn(textGroupResult);

        mvc.perform(get("/api/v1/text?url=" + mockUrl + "&textType=TEXT&textGroupUnit=8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groupTextList[0]", is("bbddEhhl")))
                .andExpect(jsonPath("$.groupTextList[1]", is("lmmooSTT")))
                .andExpect(jsonPath("$.otherText", is("ttyy")));

        verify(textHandleService, times(1)).createHandleResult(textHandleParam);
    }

    @Test
    @DisplayName("getHandleTextTest() - Invalid Parameter")
    public void getHandleTextTest2() throws Exception {

        mvc.perform(get("/api/v1/text?url=" + mockUrl))
                .andExpect(status().isBadRequest());

        verify(textHandleService, never()).createHandleResult(any(TextHandleParam.class));
    }

    @Test
    @DisplayName("getHandleTextTest() - TextHandleService ParamException")
    public void getHandleTextTest3() throws Exception {

        TextHandleParam textHandleParam = new TextHandleParam();
        textHandleParam.setUrl(mockUrl);
        textHandleParam.setTextType(TextType.TEXT);
        textHandleParam.setTextGroupUnit(8);

        doThrow(new ParamException("test"))
                .when(textHandleService).createHandleResult(textHandleParam);

        mvc.perform(get("/api/v1/text?url=" + mockUrl + "&textType=TEXT&textGroupUnit=8"))
                .andExpect(status().isBadRequest());

        verify(textHandleService, times(1)).createHandleResult(textHandleParam);
    }

    @Test
    @DisplayName("getHandleTextTest() - TextHandleService ServerException")
    public void getHandleTextTest4() throws Exception {

        TextHandleParam textHandleParam = new TextHandleParam();
        textHandleParam.setUrl(mockUrl);
        textHandleParam.setTextType(TextType.TEXT);
        textHandleParam.setTextGroupUnit(8);

        doThrow(new IllegalAccessException())
                .when(textHandleService).createHandleResult(textHandleParam);

        mvc.perform(get("/api/v1/text?url=" + mockUrl + "&textType=TEXT&textGroupUnit=8"))
                .andExpect(status().isInternalServerError());

        verify(textHandleService, times(1)).createHandleResult(textHandleParam);
    }

}
