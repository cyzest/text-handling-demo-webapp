package com.cyzest.texthandler.service;

import com.cyzest.texthandler.dto.TextGroupResult;
import com.cyzest.texthandler.dto.TextHandleParam;
import com.cyzest.texthandler.dto.TextType;
import com.cyzest.texthandler.exception.ParamException;
import com.cyzest.texthandler.handler.TextConverter;
import com.cyzest.texthandler.handler.TextSorter;
import com.cyzest.texthandler.reader.UrlHtmlReader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TextHandleService {

    private final UrlHtmlReader urlHtmlReader;

    private final Map<TextType, TextConverter> textTypeTextConvertMap;

    private final TextSorter textSorter;

    public TextGroupResult createHandleResult(TextHandleParam textHandleParam) throws Exception {

        TextGroupResult textGroupResult = new TextGroupResult();

        if (textHandleParam != null) {

            String urlContent;

            try {
                urlContent = Optional.ofNullable(urlHtmlReader.read(textHandleParam.getUrl()))
                        .orElseThrow(() -> new ParamException("html content null"));
            } catch (ConnectException ex) {
                throw new ParamException("url is not available");
            }

            String convertedText = Optional.ofNullable(textTypeTextConvertMap.get(textHandleParam.getTextType()))
                    .orElseThrow(() -> new ParamException("textType is not available"))
                    .convert(urlContent);

            String sortedText = textSorter.sort(convertedText);

            textGroupResult = getTextGroupResult(sortedText, textHandleParam.getTextGroupUnit());
        }

        return textGroupResult;
    }

    private TextGroupResult getTextGroupResult(String text, int textGroupUnit) {

        TextGroupResult textGroupResult = new TextGroupResult();

        List<String> groupTextList = new ArrayList<>();

        int length = text.length();

        for (int i = 0; i < length; i += textGroupUnit) {

            String groupText = text.substring(i, Math.min(length, i + textGroupUnit));

            if (groupText.length() >= textGroupUnit) {
                groupTextList.add(groupText);
            } else {
                textGroupResult.setOtherText(groupText);
            }
        }

        textGroupResult.setGroupTextList(groupTextList);

        return textGroupResult;
    }

}
