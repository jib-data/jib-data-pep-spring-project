package com.example.exception;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;


public class ExceptionBody{
    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime timeStamp;

    public ExceptionBody(String message, HttpStatus httpStatus, ZonedDateTime timeStamp){
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;        
    }

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    public void setHttpStatus(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }
    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

    public void setTimeStamp(ZonedDateTime timeStamp){
        this.timeStamp = timeStamp;
    }
    public ZonedDateTime getTimeStamp(){
        return timeStamp;
    }
}