package com.example.github.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourseNotFoundException extends RuntimeException{
    public ResourseNotFoundException(String message){
        super(message);
    }
    public ResourseNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }

}
