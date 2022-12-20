package com.Barbers.BarbersBackend.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String msg){
        super(msg);
    }
}
