package dev.darsh.mockito.exception;

public class UserNotSavedException extends RuntimeException{
    private String message;

    public UserNotSavedException(String message){
        super(message);
    }
}
