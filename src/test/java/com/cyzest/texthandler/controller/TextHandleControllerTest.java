package com.cyzest.texthandler.controller;

import com.cyzest.texthandler.dto.TextGroupResult;
import com.cyzest.texthandler.dto.TextHandleParam;
import com.cyzest.texthandler.exception.ParamException;
import com.cyzest.texthandler.service.TextHandleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TextHandleControllerTest {

    private MockMvc mvc;

    @Mock
    private TextHandleService textHandleService;

    @InjectMocks
    private TextHandleController textHandleController;

    private String mockUrl;

    @BeforeEach
    public void setUp() {
        mockUrl = "http://www.cyzest.com";
        mvc = MockMvcBuilders.standaloneSetup(textHandleController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    public void getHandleTextTest() throws Exception {

        TextGroupResult textGroupResult = new TextGroupResult();
        textGroupResult.setGroupTextList(Arrays.asList("bbddEhhl", "lmmooSTT"));
        textGroupResult.setOtherText("ttyy");

        when(textHandleService.createHandleResult(any(TextHandleParam.class))).thenReturn(textGroupResult);

        mvc.perform(get("/api/v1/text?url=" + mockUrl + "&textType=TEXT&textGroupUnit=8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groupTextList[0]", is("bbddEhhl")))
                .andExpect(jsonPath("$.groupTextList[1]", is("lmmooSTT")))
                .andExpect(jsonPath("$.otherText", is("ttyy")));

        verify(textHandleService, times(1)).createHandleResult(any(TextHandleParam.class));
    }

    @Test
    public void getHandleTextExceptionTest() throws Exception {

        doThrow(new ParamException("test"))
                .when(textHandleService).createHandleResult(any(TextHandleParam.class));

        mvc.perform(get("/api/v1/text?url=" + mockUrl + "&textType=TEXT&textGroupUnit=8"))
                .andExpect(status().isBadRequest());

        verify(textHandleService, times(1)).createHandleResult(any(TextHandleParam.class));
    }

}
