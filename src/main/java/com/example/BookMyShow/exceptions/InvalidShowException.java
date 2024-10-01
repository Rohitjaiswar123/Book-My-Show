package com.example.BookMyShow.exceptions;

public class InvalidShowException extends RuntimeException{
    public InvalidShowException(String message){
        super(message);
    }
}
