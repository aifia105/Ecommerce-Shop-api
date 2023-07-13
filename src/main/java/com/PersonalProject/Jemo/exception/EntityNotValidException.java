package com.PersonalProject.Jemo.exception;

import lombok.Getter;

import java.util.List;

public class EntityNotValidException extends RuntimeException {


    @Getter
    private ErrorCodes errorCodes;
    @Getter
    private List<String> errors;

    public EntityNotValidException(String message){
        super(message);
    }

    public EntityNotValidException(String message, Throwable cause){
        super(message,cause);
    }

    public EntityNotValidException(String message, Throwable cause, ErrorCodes errorCodes){
        super(message,cause);
        this.errorCodes = errorCodes;
    }

    public EntityNotValidException(String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes = errorCodes;
    }

    public EntityNotValidException(String message, ErrorCodes errorCodes,List<String> errors){
        super(message);
        this.errorCodes = errorCodes;
        this.errors = errors;
    }


}
