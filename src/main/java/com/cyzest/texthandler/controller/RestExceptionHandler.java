package com.cyzest.texthandler.controller;

import com.cyzest.texthandler.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

@Slf4j
@Component
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({
            ParamException.class,
            BindException.class,
            HttpMediaTypeException.class,
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            HttpRequestMethodNotSupportedException.class,
            MissingServletRequestParameterException.class
    })
    public ResponseEntity badRequestExceptionHandler(Exception ex) {

        log.debug("Bad Request Exception Handling...", ex);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity errorExceptionHandler(Throwable ex) {

        log.error("Internal Server Error Exception Handling...", ex);

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
