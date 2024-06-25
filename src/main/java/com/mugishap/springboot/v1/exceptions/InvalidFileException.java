package com.mugishap.springboot.v1.exceptions;

public class InvalidFileException extends Exception{
    public InvalidFileException(String message) {
        super(message);
    }
}
