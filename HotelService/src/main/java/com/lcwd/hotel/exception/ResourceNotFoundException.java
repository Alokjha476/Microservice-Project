package com.lcwd.hotel.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not found exception !!");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
