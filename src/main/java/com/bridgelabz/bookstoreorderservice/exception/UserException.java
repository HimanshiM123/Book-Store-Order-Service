package com.bridgelabz.bookstoreorderservice.exception;

public class UserException extends RuntimeException {
    private int statusCode;
    private String statusMessage;

    public UserException(int statusCode, String statusMessage){
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
