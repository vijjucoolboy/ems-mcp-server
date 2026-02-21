package com.ems.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
