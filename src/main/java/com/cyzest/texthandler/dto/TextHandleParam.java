package com.cyzest.texthandler.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
public class TextHandleParam {

    @NotEmpty
    private String url;

    @NotNull
    private TextType textType;

    @Min(1)
    @NotNull
    private Integer textGroupUnit;

}
