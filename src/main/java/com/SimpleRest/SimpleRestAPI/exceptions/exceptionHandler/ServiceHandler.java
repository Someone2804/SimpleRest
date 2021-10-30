package com.SimpleRest.SimpleRestAPI.exceptions.exceptionHandler;

import com.SimpleRest.SimpleRestAPI.exceptions.NotFoundException;
import com.SimpleRest.SimpleRestAPI.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ServiceHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String notFound(NotFoundException ex){
        return ex.getMessage();
    }
}
