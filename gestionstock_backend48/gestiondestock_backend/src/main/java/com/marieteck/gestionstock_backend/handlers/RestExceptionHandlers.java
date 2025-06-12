package com.marieteck.gestionstock_backend.handlers;

import com.marieteck.gestionstock_backend.exception.EntityNotFoundException;
import com.marieteck.gestionstock_backend.exception.ErrorCodes;
import com.marieteck.gestionstock_backend.exception.InvalidEntityException;
import com.marieteck.gestionstock_backend.exception.InvalidOperationException;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice
public class RestExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { EntityNotFoundException.class })
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception, WebRequest webRequest) {
        final HttpStatus notFound =  HttpStatus.NOT_FOUND;
        final ErrorDto errorDto= ErrorDto.builder()
                .errorCode(exception.getErrorCode())
                .HttpCode(notFound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, notFound);

    }


    @ExceptionHandler(value = {InvalidEntityException.class})
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception, WebRequest webRequest) {
        final HttpStatus badRequest=  HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .errorCode(exception.getErrorCode())
                .HttpCode(badRequest.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, badRequest);
    }


    @ExceptionHandler(value = { InvalidOperationException.class })
    public ResponseEntity<ErrorDto> handleException(InvalidOperationException exception, WebRequest webRequest) {
        final HttpStatus badRequest=  HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .errorCode(exception.getErrorCode())
                .HttpCode(badRequest.value())
                .message(exception.getMessage())
                .build();


        return new ResponseEntity<>(errorDto, badRequest);
    }




}
