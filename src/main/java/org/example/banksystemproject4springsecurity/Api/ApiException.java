package org.example.banksystemproject4springsecurity.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
