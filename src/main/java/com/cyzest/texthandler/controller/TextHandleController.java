package com.cyzest.texthandler.controller;

import com.cyzest.texthandler.dto.TextHandleParam;
import com.cyzest.texthandler.dto.TextGroupResult;
import com.cyzest.texthandler.service.TextHandleService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api("텍스트")
@RestController
@AllArgsConstructor
public class TextHandleController {

    private TextHandleService textHandleService;

    @GetMapping("/api/v1/text")
    public TextGroupResult getHandleText(@ModelAttribute @Valid TextHandleParam textHandleParam) throws Exception {
        return textHandleService.createHandleResult(textHandleParam);
    }

}
