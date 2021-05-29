package com.training.common;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

@Log4j
@ControllerAdvice
@EnableWebMvc
public class ExceptionController {

    @ExceptionHandler(value = Exception.class)
    public String handleError(HttpServletRequest request, Exception exception) {
        log.error("Request: " + request.getRequestURL() + " raised " + exception);
        return "error";
    }
}
