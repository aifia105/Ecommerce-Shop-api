package com.PersonalProject.Jemo.exception;

import lombok.Getter;

public class OperationNotValidException extends RuntimeException {

    @Getter
    private ErrorCodes errorCodes;

    public OperationNotValidException(String message){
        super(message);
    }

    public OperationNotValidException(String message, Throwable cause){
        super(message,cause);
    }

    public OperationNotValidException(String message, Throwable cause, ErrorCodes errorCodes){
        super(message,cause);
        this.errorCodes = errorCodes;
    }

    public OperationNotValidException(String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes = errorCodes;
    }

}
