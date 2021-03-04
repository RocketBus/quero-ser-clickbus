package com.clickbus.challenge.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.clickbus.challenge.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request){
        String error = e.getLocalizedMessage();
        if(error == null) {
            error = e.toString();
        }
        ErrorMessage errorMessage = new ErrorMessage(new Date(), error );
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {PlacesServiceException.class})
    public ResponseEntity<Object> handlePlacesServiceException(PlacesServiceException e, WebRequest request){
        String error = e.getLocalizedMessage();
        if(error == null) {
            error = e.toString();
        }
        ErrorMessage errorMessage = new ErrorMessage(new Date(), error );
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
