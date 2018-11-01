package com.cyzest.texthandler.config;

import com.cyzest.texthandler.dto.TextType;
import com.cyzest.texthandler.handler.*;
import com.cyzest.texthandler.reader.JsoupUrlHtmlReader;
import com.cyzest.texthandler.reader.UrlHtmlReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class AppConfig {

    @Bean
    public UrlHtmlReader urlHtmlReader() {
        return new JsoupUrlHtmlReader();
    }

    @Bean
    public Map<TextType, TextConverter> textTypeTextConvertMap() {
        Map<TextType, TextConverter> textTypeTextConvertMap = new ConcurrentHashMap<>();
        textTypeTextConvertMap.put(TextType.TEXT, new AlphabetOrNumTextConverter());
        textTypeTextConvertMap.put(TextType.HTML, new HtmlTagDeleteTextConverter());
        return textTypeTextConvertMap;
    }

    @Bean
    public TextSorter textSorter() {
        return new CustomTextSorter();
    }

}
