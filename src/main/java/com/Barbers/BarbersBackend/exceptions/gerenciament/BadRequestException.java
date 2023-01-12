package com.Barbers.BarbersBackend.exceptions.gerenciament;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String msg){
        super(msg);
    }
}
