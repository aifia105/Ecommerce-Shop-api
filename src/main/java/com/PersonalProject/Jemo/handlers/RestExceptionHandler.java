package com.PersonalProject.Jemo.handlers;

import com.PersonalProject.Jemo.exception.EntityNotFoundException;
import com.PersonalProject.Jemo.exception.EntityNotValidException;
import com.PersonalProject.Jemo.exception.ErrorCodes;
import com.PersonalProject.Jemo.exception.OperationNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handlerException(EntityNotFoundException exception, WebRequest request){

        final HttpStatus notfound = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .errorCodes(exception.getErrorCodes())
                .httpCode(notfound.value())
                .message(exception.getMessage()).build();

        return new ResponseEntity<>(errorDto, notfound);
    }

    @ExceptionHandler(EntityNotValidException.class)
    public ResponseEntity<ErrorDto> handlerException(EntityNotValidException exception, WebRequest request){

        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .errorCodes(exception.getErrorCodes())
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(exception.getErrors()).build();

        return new ResponseEntity<>(errorDto, badRequest);
    }


    @ExceptionHandler(OperationNotValidException.class)
    public ResponseEntity<ErrorDto>  handlerException(OperationNotValidException exception, WebRequest request){

        final HttpStatus notfound = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .errorCodes(exception.getErrorCodes())
                .httpCode(notfound.value())
                .message(exception.getMessage()).build();

        return new ResponseEntity<>(errorDto, notfound);
    }

}
