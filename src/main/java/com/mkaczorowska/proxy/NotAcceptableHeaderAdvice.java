package com.mkaczorowska.proxy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotAcceptableHeaderAdvice {
    @ExceptionHandler(NotAcceptableHeaderException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String notAcceptableHeaderHandler(NotAcceptableHeaderException ex) {
        return ex.getMessage();
    }
}
