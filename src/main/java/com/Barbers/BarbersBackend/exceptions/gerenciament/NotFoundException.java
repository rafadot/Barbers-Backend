package com.Barbers.BarbersBackend.exceptions.gerenciament;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String msg){
        super(msg);
    }
}
