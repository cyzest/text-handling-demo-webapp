package com.cyzest.texthandler.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TextGroupResult {

    private List<String> groupTextList;
    private String otherText;

}
