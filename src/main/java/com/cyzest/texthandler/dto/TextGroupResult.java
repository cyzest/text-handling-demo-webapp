package com.cyzest.texthandler.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class TextGroupResult {

    private List<String> groupTextList;
    private String otherText;

}
