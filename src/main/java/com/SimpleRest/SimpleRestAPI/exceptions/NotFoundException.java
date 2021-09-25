package com.SimpleRest.SimpleRestAPI.exceptions;

public class NotFoundException extends ServiceException{

    public NotFoundException(String message) {
        super(message);
    }
}
